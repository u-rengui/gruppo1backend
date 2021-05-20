$(document).ready(function () {
/*
     function getListaClienti() {
		$.get('/api/clienti/findAlls', function(res) {
			for(let i = 0; i < res.length; i++) { 
				$(`<tr class='row-${res[i].id}'>
						<td>${res[i].ragionesociale}</td>
						<td>${res[i].partitaiva}</td>
						<td>${res[i].indirizzo}</td>
                        <td>${res[i].email}</td>
						<td>${res[i].ntel}</td>
					</tr>`).appendTo('#lista-clienti');
			}
		})
	} */

    /* function cleanLista(){
        $('#lista-clienti').html('');
    } */
    
    $('#add').click(function() {
		const v = {
            ragionesociale: $('#ragionesociale').val(),
			email: $('#email').val(),
			partitaiva: $('#partitaiva').val(),
			indirizzo: $('#indirizzo').val(),
			ntel: $('#ntel').val()
		}
        //console.log(v);
        addCliente(v);        
    })

    function addCliente(v) {
        $.ajax({                    
            url: "/api/clienti/addCliente",
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

        // $.post("151.84.70.188:8099/api/clienti/addCliente" , JSON.stringify(v), function(ris){
        //     if(ris.message === "OK"){
        //         alert('ok');
        //     }else{
        //         alert('error');
        //     }
        // })
    }

    $('#edit').click(function() {
		const v = {
            id: $('#editid').val(),
			ragionesociale: $('#editragionesociale').val(),
			email: $('#editemail').val(),
			partitaiva: $('#editpartitaiva').val(),
			indirizzo: $('#editindirizzo').val(),
			ntel: $('#editntel').val()
		}        
        editCliente(v);        
    })

    function editCliente(v) {
        $.ajax({                    
            url: "/api/clienti/updateCliente",
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