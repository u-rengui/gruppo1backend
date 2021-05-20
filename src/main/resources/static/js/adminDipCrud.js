var $table = $('#table')
  
	function operateFormatter(value, row, index) {
	  return [
		'<a class="edit" href="javascript:void(0)" title="Edit">',
		'<i class="bi bi-pencil-fill"></i>',
		'</a>  ',
		'<a class="remove" href="javascript:void(0)" title="Remove">',
		'<i class="fa fa-trash"></i>',
		'</a>'
	  ].join('')
	}
  
	window.operateEvents = {
	  'click .edit': function (e, value, row, index) {		
		document.getElementById('editid').value = row.id,
		document.getElementById('editcognome').value = row.cognome,
		document.getElementById('editnome').value = row.nome,
		document.getElementById('editdatadinascita').value = row.datadinascita,
		document.getElementById('editstipendio').value = row.stipendio,
		document.getElementById('editdataassunzione').value = row.dataassunzione,
        document.getElementById('editlistruolo').value = row.ruolo.id,
        console.log(row.ruolo.id),
		$('#editModal').modal('show')

	  },
	  'click .remove': function (e, value, row, index) {
		$.ajax({
			url: "/api/personale/deletePersonale/" + row.id,
            headers: {
                Accept: 'application/json',
                'Content-Type':'application/json'
            },
            type: "DELETE",
            contentType: "application/json",
            data: JSON.stringify(row.id),
            contentType: "application/json",
            success: function (data) {
				$table.bootstrapTable('remove', {
		  		field: 'id',
		  		values: [row.id]
				})
			}
		})		
	  }
	}