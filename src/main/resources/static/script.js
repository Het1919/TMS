    function toggleBankStatus(element) {
        var bankName = element.getAttribute('data-bank-name');
        var currentStatus = element.getAttribute('data-bank-status');

        var newStatus = currentStatus === 'true' ? 'false' : 'true';

        // Update the UI
        element.classList.remove(currentStatus === 'true' ? 'active' : 'inactive');
        element.classList.add(newStatus === 'true' ? 'active' : 'inactive');
        element.textContent = newStatus === 'true' ? 'Active' : 'Inactive';

        // Send an AJAX request to update the backend
        fetch(`/toggleBankStatus?bankName=${bankName}&newStatus=${newStatus}`, {
            method: 'POST'
        })
        .then(response => {
            if (!response.ok) {
                console.error('Failed to update bank status');
            }
        })
        .catch(error => {
            console.error('Error updating bank status:', error);
        });
    }
    
    function updateBankStatus(selectElement) {
    	var statusId = selectElement.id    	
    	var info = statusId.split("-");
        var bankName = info[1];// Extract bank name from ID
        var newStatus = selectElement.value === 'true';
        var tenantId = info[2];
       /* var selectedOption = selectElement.options[selectElement.selectedIndex];
    	var op1 = $(`#${statusId}-1`);
    	var op2 = $(`#${statusId}-2`);*/
    	
       /*  $(document).ready(()=>{
        	var selectEle = $(`#${statusId}`);
        	
        	if(!newStatus){
        		selectEle.css("background-color", "#ffb8b8");
        		selectEle.css("color", "#ff3838");
        		
        		op1.css("background-color", "#fff");
        		op1.css("color", "#000");
        	}
        	else{
        		selectEle.css("background-color", "#a5ffba");
        		selectEle.css("color", "#065737");
        		
        		op2.css("background-color", "#fff");
        		op2.css("color", "#000");
        	}

        	//selectEle.css.backGround = "Red";
        }); */
        
        //console.log(info);
        

        // Send an AJAX request to update the backend
       fetch(`/updateBankStatus?bankName=${bankName}&newStatus=${newStatus}&tenantId=${tenantId}`, {
    		method: 'POST'
		})
        .then(response => {
            if (!response.ok) {
                console.error('Failed to update bank status');
            } else {
                // Update the UI
                var statusElement = document.querySelector(`.bank-status-toggle[data-bank-name="${bankName}"]`);
                statusElement.classList.remove(newStatus ? 'inactive' : 'active');
                statusElement.classList.add(newStatus ? 'active' : 'inactive');
                statusElement.setAttribute('data-bank-status', newStatus);
               
            }
        })
        .catch(error => {
            console.error('Error updating bank status:', error);
        });
    }
    
    function updateGlobalBankStatus(selectElement) {
        var bankName = selectElement.id.substring(7); // Extract bank name from ID
        var newStatus = selectElement.value === 'true';
        
       // var selectedOption = selectElement.options[selectElement.selectedIndex];
        // Send an AJAX request to update the backend
       fetch(`/updateGlobalBankStatus?bankName=${bankName}&newStatus=${newStatus}`, {
    method: 'POST'
})
        .then(response => {
            if (!response.ok) {
                console.error('Failed to update bank status');
            } else {
                // Update the UI
                var statusElement = document.querySelector(`.bank-status-toggle[data-bank-name="${bankName}"]`);
                statusElement.classList.remove(newStatus ? 'inactive' : 'active');
                statusElement.classList.add(newStatus ? 'active' : 'inactive');
                statusElement.setAttribute('data-bank-status', newStatus);
                
            }
        })
        .catch(error => {
            console.error('Error updating bank status:', error);
        });
    }

