$(document).ready(function () {
	
	function getListaRouli() {
		$.get('/api/ruoli/findAll', function(res) {
			for(let i = 0; i < res.length; i++) { 
				$(`<option value='${res[i].id}'>
					${res[i].mansione}
					</option >`).appendTo('#listruolo');
			}
		})
	} 

	getListaRouli();

	function getEditListaRouli() {
		$.get('/api/ruoli/findAll', function(res) {
			for(let i = 0; i < res.length; i++) { 
				$(`<option value='${res[i].id}'>
					${res[i].mansione}
					</option >`).appendTo('#editlistruolo');
			}
		})
	}

	getEditListaRouli();


	//Add

	$('#add').click(function() {

		const v = {
			cognome: $('#cognome').val(),
			nome: $('#nome').val(),
			ddn: $('#datadinascita').val(),
			stipendio: $('#stipendio').val(),
			dataassunzione: $('#dataassunzione').val(),
			idruolo: $('#listruolo').val()
		}
        //console.log(v);
        addDip(v);
        
    })

	function addDip(v){
		$.ajax({                    
            url: "/api/personale/addPersonale",
            headers: {
                Accept: 'application/json',
                'Content-Type':'application/json'
            },
            type: "POST",
            contentType: "application/json",
            data: JSON.stringify(v),
            contentType: "application/json",
            success: function (data) {
                //alert("Successfully Registered..");
                //cleanLista();
                //getListaClienti();
                $('#table').bootstrapTable('refresh');
                $('#addModal').modal('hide');
                $('#addModal').on('hidden.bs.modal', function () {
                    $(this).find('form').trigger('reset');
                })
            },
            error: function (xhRequest, ErrorText, thrownError) {
                alert("Failed to process correctly, please try again");
            }
        });
	}

	// Edit

	$('#edit').click(function() {
		const v = {
            id: $('#editid').val(),
			cognome: $('#editcognome').val(),
			nome: $('#editnome').val(),
			ddn: $('#editdatadinascita').val(),
			stipendio: $('#editstipendio').val(),
			dataassunzione: $('#editdataassunzione').val(),
			idruolo: $('#editlistruolo').val()
		}        
        editDip(v);        
    })

    function editDip(v) {
        $.ajax({                    
            url: "/api/personale/updatePersonale",
            headers: {
                Accept: 'application/json',
                'Content-Type':'application/json'
            },
            type: "PUT",
            contentType: "application/json",
            data: JSON.stringify(v),
            contentType: "application/json",
            success: function (data) {
                //alert("Successfully Registered..");
                //cleanLista();
                //getListaClienti();
                $('#table').bootstrapTable('refresh');
                $('#editModal').modal('hide');
                $('#editModal').on('hidden.bs.modal', function () {
                    $(this).find('form').trigger('reset');
                })
            },
            error: function (xhRequest, ErrorText, thrownError) {
                alert("Failed to process correctly, please try again");
            }
        });

        
    }

})