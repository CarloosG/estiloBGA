const clientTableBody = document.getElementById('client-table-body');
const addClientForm = document.getElementById('add-client-form');
const apiUrl = 'http://localhost:8094/api/clientes'; // Ajusta la URL si es diferente

const editFormContainer = document.getElementById('edit-form-container');
const editClientForm = document.getElementById('edit-client-form');
const editClienteIdInput = document.getElementById('edit-clienteId');
const editNombresInput = document.getElementById('edit-nombres');
const editApellidosInput = document.getElementById('edit-apellidos');
const editNumeroDocumentoInput = document.getElementById('edit-numeroDocumento');
const editCorreoElectronicoInput = document.getElementById('edit-correoElectronico');
const editCelularInput = document.getElementById('edit-celular');  

// Función para obtener la lista de clientes
async function fetchClientes() {
    try {
        const response = await fetch(`${apiUrl}/list`);
        if (!response.ok) {
            throw new Error(`HTTP error! status: ${response.status}`);
        }
        const clientes = await response.json();
        displayClientes(clientes);
    } catch (error) {
        console.error('Error fetching clientes:', error);
        alert('Error al cargar la lista de clientes.');
    }
}

// Función para obtener la lista de clientes
async function fetchClientes() {
    try {
        const response = await fetch(`${apiUrl}/list`);
        if (!response.ok) {
            throw new Error(`HTTP error! status: ${response.status}`);
        }
        const clientes = await response.json();
        displayClientes(clientes);
    } catch (error) {
        console.error('Error fetching clientes:', error);
        alert('Error al cargar la lista de clientes.');
    }
}

// Función para mostrar los clientes en la tabla
function displayClientes(clientes) {
    clientTableBody.innerHTML = ''; // Limpiar la tabla
    clientes.forEach(cliente => {
        const row = clientTableBody.insertRow();
        row.insertCell().textContent = cliente.id;
        row.insertCell().textContent = cliente.usuario.nombres;
        row.insertCell().textContent = cliente.usuario.apellidos;
        row.insertCell().textContent = cliente.usuario.numeroDocumento;
        row.insertCell().textContent = cliente.usuario.correoElectronico;
        row.insertCell().textContent = cliente.usuario.celular;
        row.insertCell().textContent = cliente.usuario.fechaRegistro;

        const actionsCell = row.insertCell();

        // Botón Editar
        const editButton = document.createElement('button');
        editButton.textContent = 'Editar';
        editButton.classList.add('btn', 'btn-primary', 'btn-sm', 'mr-2'); // Añadimos margen derecho
        editButton.addEventListener('click', () => loadClienteForEdit(cliente.id));
        actionsCell.appendChild(editButton);

        // Botón Eliminar
        const deleteButton = document.createElement('button');
        deleteButton.textContent = 'Eliminar';
        deleteButton.classList.add('btn', 'btn-danger', 'btn-sm', 'delete-btn');
        deleteButton.addEventListener('click', () => deleteCliente(cliente.id));
        actionsCell.appendChild(deleteButton);
    });
}

// Función para cargar los datos del cliente en el formulario de edición
async function loadClienteForEdit(id) {
    try {
        const response = await fetch(`${apiUrl}/list/${id}`);
        if (!response.ok) {
            throw new Error(`HTTP error! status: ${response.status}`);
        }
        const cliente = await response.json();
        populateEditForm(cliente);
    } catch (error) {
        console.error('Error fetching cliente for edit:', error);
        alert('Error al cargar los datos del cliente para editar.');
    }
}

// Función para llenar el formulario de edición
let fechaRegistroOriginal = null; // Variable para almacenar la fecha de registro original

function populateEditForm(cliente) {
    editClienteIdInput.value = cliente.id;
    editNombresInput.value = cliente.usuario.nombres;
    editApellidosInput.value = cliente.usuario.apellidos;
    editNumeroDocumentoInput.value = cliente.usuario.numeroDocumento;
    editCorreoElectronicoInput.value = cliente.usuario.correoElectronico;
    editCelularInput.value = cliente.usuario.celular;
    fechaRegistroOriginal = cliente.usuario.fechaRegistro; // Guardamos la fecha de registro original
    editFormContainer.style.display = 'block'; // Mostrar el formulario de edición
}

function hideEditForm() {
    editFormContainer.style.display = 'none';
    editClientForm.reset();
}

// Función para agregar un nuevo cliente (con la fecha de registro opcional)
addClientForm.addEventListener('submit', async (event) => {
    event.preventDefault();

    const nombres = document.getElementById('nombres').value;
    const apellidos = document.getElementById('apellidos').value;
    const numeroDocumento = document.getElementById('numeroDocumento').value;
    const correoElectronico = document.getElementById('correoElectronico').value;
    const password = document.getElementById('password').value;
    const celular = document.getElementById('celular').value;

    const nuevoCliente = {
        usuario: {
            nombres: nombres,
            apellidos: apellidos,
            numeroDocumento: numeroDocumento,
            correoElectronico: correoElectronico,
            password: password,
            celular: celular,
            rol: 'cliente',
            fechaRegistro: new Date().toISOString().split('T')[0] // Asegúrate de tener esta línea si gestionas la fecha en el frontend
        }
    };

    try {
        const response = await fetch(apiUrl + '/', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(nuevoCliente),
        });

        if (response.ok) {
            alert('Cliente agregado exitosamente.');
            addClientForm.reset();
            fetchClientes(); // Recargar la lista de clientes
        } else {
            const errorData = await response.json();
            console.error('Error al agregar cliente:', errorData);
            alert('Error al agregar cliente.');
        }
    } catch (error) {
        console.error('Error al enviar la petición:', error);
        alert('Error de conexión al servidor.');
    }
});

// Función para eliminar un cliente
async function deleteCliente(id) {
    if (confirm(`¿Estás seguro de que quieres eliminar el cliente con ID ${id}?`)) {
        try {
            const response = await fetch(`${apiUrl}/${id}`, {
                method: 'DELETE',
            });

            if (response.status === 204) {
                alert(`Cliente con ID ${id} eliminado exitosamente.`);
                fetchClientes(); // Recargar la lista de clientes
            } else if (response.status === 404) {
                alert(`No se encontró el cliente con ID ${id}.`);
            } else {
                const errorData = await response.json();
                console.error('Error al eliminar cliente:', errorData);
                alert('Error al eliminar cliente.');
            }
        } catch (error) {
            console.error('Error al enviar la petición:', error);
            alert('Error de conexión al servidor.');
        }
    }
}

// Evento submit para el formulario de edición
editClientForm.addEventListener('submit', async (event) => {
    event.preventDefault();

    const clienteId = editClienteIdInput.value;
    const nombres = editNombresInput.value;
    const apellidos = editApellidosInput.value;
    const numeroDocumento = editNumeroDocumentoInput.value;
    const correoElectronico = editCorreoElectronicoInput.value;
    const celular = editCelularInput.value;

    const clienteActualizado = {
        id: parseInt(clienteId),
        usuario: {
            id: parseInt(clienteId), // Asumiendo que el ID del usuario es el mismo que el del cliente (revisa tu lógica)
            nombres: nombres,
            apellidos: apellidos,
            numeroDocumento: numeroDocumento,
            correoElectronico: correoElectronico,
            celular: celular,
            fechaRegistro: fechaRegistroOriginal // Enviamos la fecha de registro original
            // No enviamos password ni rol en la edición (depende de tu lógica)
        }
    };

    try {
        const response = await fetch(apiUrl + '/', { // Usa el endpoint PUT
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(clienteActualizado),
        });

        if (response.ok) {
            alert(`Cliente con ID ${clienteId} actualizado exitosamente.`);
            hideEditForm();
            fetchClientes(); // Recargar la lista de clientes
        } else {
            const errorData = await response.json();
            console.error('Error al actualizar cliente:', errorData);
            alert('Error al actualizar cliente.');
        }
    } catch (error) {
        console.error('Error al enviar la petición de actualización:', error);
        alert('Error de conexión al servidor.');
    }
});

// Cargar la lista de clientes al cargar la página
fetchClientes();