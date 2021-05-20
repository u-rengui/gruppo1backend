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
		document.getElementById('editragionesociale').value = row.ragionesociale,
		document.getElementById('editemail').value = row.email,
		document.getElementById('editpartitaiva').value = row.partitaiva,
		document.getElementById('editindirizzo').value = row.indirizzo,
		document.getElementById('editntel').value = row.ntel,
		$('#editModal').modal('show')

	  },
	  'click .remove': function (e, value, row, index) {
		$.ajax({
			url: "/api/clienti/deleteCliente/" + row.id,
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