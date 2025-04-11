
// URLs de la API
const API_URL = 'http://localhost:8094/api';
const CITAS_API = `${API_URL}/citas`;
const CLIENTES_API = `${API_URL}/clientes/list`;
const ESTILISTAS_API = `${API_URL}/estilistas/list`;
const SERVICIOS_API = `${API_URL}/servicios/list`;

// Variables globales
let citas = [];
let clientes = [];
let estilistas = [];
let currentCitaId = null;
let citaAEliminar = null;

// Elementos del DOM
const citaForm = document.getElementById('cita-form');
const clienteSelect = document.getElementById('cliente');
const formTitle = document.getElementById('form-title');
const estilistaSelect = document.getElementById('estilista');
const fechaCitaInput = document.getElementById('fecha-cita');
const horaCitaInput = document.getElementById('hora-cita');
const citasList = document.getElementById('citas-list');
const noCitasMessage = document.getElementById('no-citas-message');
const searchInput = document.getElementById('search-input');
const saveBtn = document.getElementById('save-btn');
const cancelBtn = document.getElementById('cancel-btn');
const deleteModal = document.getElementById('delete-modal');
const confirmDeleteBtn = document.getElementById('confirm-delete');
const cancelDeleteBtn = document.getElementById('cancel-delete');
const clienteOpcionSelect = document.getElementById('cliente-opcion');
const clienteExistenteGroup = document.getElementById('cliente-existente');
const clienteNuevoGroup = document.getElementById('cliente-nuevo');
// Cargar datos iniciales
document.addEventListener('DOMContentLoaded', () => {
  fetchCitas();
  fetchClientes();
  fetchEstilistas();
  
  // Configurar manejadores de eventos
  citaForm.addEventListener('submit', handleFormSubmit);
  cancelBtn.addEventListener('click', resetForm);
  searchInput.addEventListener('input', filterCitas);
  confirmDeleteBtn.addEventListener('click', confirmDelete);
  cancelDeleteBtn.addEventListener('click', () => {
    deleteModal.classList.add('hidden');
    citaAEliminar = null;
  });
});
clienteOpcionSelect.addEventListener('change', () => {
  if (clienteOpcionSelect.value === 'nuevo') {
    clienteExistenteGroup.classList.add('hidden');
    clienteNuevoGroup.classList.remove('hidden');
  } else {
    clienteExistenteGroup.classList.remove('hidden');
    clienteNuevoGroup.classList.add('hidden');
  }
});
// Obtener todas las citas
async function fetchCitas() {
  try {
    const response = await fetch(`${CITAS_API}/list`);
    if (!response.ok) throw new Error('Error al obtener las citas');
    
    citas = await response.json();
    renderCitas();
  } catch (error) {
    console.error('Error:', error);
    alert('Error al cargar las citas. Por favor, intenta de nuevo más tarde.');
  }
}

// Obtener clientes para el select
async function fetchClientes() {
  try {
    const response = await fetch(CLIENTES_API);
    if (!response.ok) throw new Error('Error al obtener los clientes');
    
    clientes = await response.json();
    populateClienteSelect();
  } catch (error) {
    console.error('Error:', error);
    alert('Error al cargar los clientes. Por favor, intenta de nuevo más tarde.');
  }
}

// Obtener estilistas para el select
async function fetchEstilistas() {
  try {
    const response = await fetch(ESTILISTAS_API);
    if (!response.ok) throw new Error('Error al obtener los estilistas');
    
    estilistas = await response.json();
    populateEstilistaSelect();
  } catch (error) {
    console.error('Error:', error);
    alert('Error al cargar los estilistas. Por favor, intenta de nuevo más tarde.');
  }
}


// Llenar el select de clientes
function populateClienteSelect() {
  clienteSelect.innerHTML = '<option value="">Seleccione un cliente</option>';
  
  clientes.forEach(cliente => {
    const option = document.createElement('option');
    option.value = cliente.id;
    option.textContent = `${cliente.usuario.nombres} ${cliente.usuario.apellidos}`;
    clienteSelect.appendChild(option);
  });
}

// Llenar el select de estilistas
function populateEstilistaSelect() {
  estilistaSelect.innerHTML = '<option value="">Seleccione un estilista</option>';
  
  estilistas.forEach(estilista => {
    const option = document.createElement('option');
    option.value = estilista.id;
    option.textContent = `${estilista.usuario.nombres} ${estilista.usuario.apellidos} - ${estilista.especializacion}`;
    estilistaSelect.appendChild(option);
  });
}


// Renderizar la lista de citas
function renderCitas() {
  if (citas.length === 0) {
    citasList.innerHTML = '';
    noCitasMessage.classList.remove('hidden');
    return;
  }
  
  noCitasMessage.classList.add('hidden');
  citasList.innerHTML = '';
  
  citas.forEach(cita => {
    console.log(cita); // ← agrega esto

    const row = document.createElement('tr');
    
    // Cliente
    const clienteCell = document.createElement('td');
    const cliente = cita.cliente && cita.cliente.usuario
    ? `${cita.cliente.usuario.nombres} ${cita.cliente.usuario.apellidos}`
    : 'No asignado';
    clienteCell.textContent = cliente;
    
    // Estilista
    const estilistaCell = document.createElement('td');
    const estilista = cita.estilista && cita.estilista.usuario
    ? `${cita.estilista.usuario.nombres} ${cita.estilista.usuario.apellidos}`
    : 'No asignado';
    estilistaCell.textContent = estilista;
    
    // Fecha y hora
    const fechaCell = document.createElement('td');
    if (cita.fechaCita) {
      const fecha = new Date(cita.fechaCita);
      fechaCell.textContent = fecha.toLocaleString('es-CO', {
        year: 'numeric',
        month: 'long',
        day: 'numeric',
        hour: '2-digit',
        minute: '2-digit'
      });
    } else {
      fechaCell.textContent = 'No definida';
    }
    

    
    // Estado de pago

    
    // Acciones
    const actionsCell = document.createElement('td');
    actionsCell.className = 'actions';
    
    const editBtn = document.createElement('button');
    editBtn.className = 'edit-btn';
    editBtn.textContent = 'Editar';
    editBtn.addEventListener('click', () => editCita(cita.id));
    
    const deleteBtn = document.createElement('button');
    deleteBtn.className = 'delete-btn';
    deleteBtn.textContent = 'Eliminar';
    deleteBtn.addEventListener('click', () => showDeleteConfirmation(cita.id));
    
    actionsCell.appendChild(editBtn);
    actionsCell.appendChild(deleteBtn);
    
    // Agregar todas las celdas a la fila
    row.appendChild(clienteCell);
    row.appendChild(estilistaCell);
    row.appendChild(fechaCell);;
    row.appendChild(actionsCell);
    
    citasList.appendChild(row);
  });
}

// Manejar envío del formulario (agregar/editar)
async function handleFormSubmit(e) {
  let clienteData;

if (clienteOpcionSelect.value === 'nuevo') {
  // Crear cliente directamente desde formulario
  clienteData = {
    usuario: {
      nombres: document.getElementById('nuevo-nombre').value,
      apellidos: document.getElementById('nuevo-apellido').value,
      celular: document.getElementById('nuevo-celular').value,
      correoElectronico: document.getElementById('nuevo-correo').value,
      rol: 'cliente',
      password: 'default123' 
    }
  };
} else {
  // Selección de cliente existente
  const clienteId = parseInt(clienteSelect.value);
  if (!clienteId) {
    alert('Selecciona un cliente o ingresa uno nuevo.');
    return;
  }
  clienteData = { id: clienteId };
}
  e.preventDefault();
  
// Validación básica
const esNuevoCliente = clienteOpcionSelect.value === 'nuevo';
if (esNuevoCliente) {
  try {
    const clienteResponse = await fetch(`${API_URL}/clientes/`, {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(clienteData)
    });

    if (!clienteResponse.ok) throw new Error('No se pudo crear el cliente');

    const nuevoClienteCreado = await clienteResponse.json();
    clienteData = { id: nuevoClienteCreado.id };

  } catch (err) {
    alert('Error al crear el cliente nuevo. Verifica los datos.');
    return;
  }
}



  // Crear objeto de fecha combinando fecha y hora
  const fechaStr = fechaCitaInput.value;
  const horaStr = horaCitaInput.value;
  const fechaHora = new Date(`${fechaStr}T${horaStr}`);
  
  // Construir el objeto cita
  const citaData = {
    cliente: clienteData,
    estilista: { id: parseInt(estilistaSelect.value) },
    fechaCita: fechaHora.toISOString()

  };
  
  if (currentCitaId) {
    citaData.id = currentCitaId;
  }
  
  try {
    const url = CITAS_API + '/';
    const method = currentCitaId ? 'PUT' : 'POST';
    
    const response = await fetch(url, {
      method: method,
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(citaData)
    });
    
    if (!response.ok) throw new Error('Error al guardar la cita');
    
    // Actualizar la interfaz
    resetForm();
    fetchCitas();
    alert(currentCitaId ? 'Cita actualizada con éxito' : 'Cita agregada con éxito');
  } catch (error) {
    console.error('Error:', error);
    alert('Error al guardar la cita. Por favor, intenta de nuevo.');
  }
}

// Editar cita existente
async function editCita(id) {
  try {
    const response = await fetch(`${CITAS_API}/list/${id}`);
    if (!response.ok) throw new Error('Error al obtener la cita');
    
    const cita = await response.json();
    currentCitaId = cita.id;
    
    // Llenar el formulario con los datos de la cita
    if (cita.cliente) clienteSelect.value = cita.cliente.id;
    if (cita.estilista) estilistaSelect.value = cita.estilista.id;
    
    if (cita.fechaCita) {
      const fecha = new Date(cita.fechaCita);
      
      // Formatear fecha YYYY-MM-DD
      const year = fecha.getFullYear();
      const month = String(fecha.getMonth() + 1).padStart(2, '0');
      const day = String(fecha.getDate()).padStart(2, '0');
      fechaCitaInput.value = `${year}-${month}-${day}`;
      
      // Formatear hora HH:MM
      const hours = String(fecha.getHours()).padStart(2, '0');
      const minutes = String(fecha.getMinutes()).padStart(2, '0');
      horaCitaInput.value = `${hours}:${minutes}`;
    }

    
    // Actualizar UI para modo edición
    formTitle.textContent = 'Editar Cita';
    saveBtn.textContent = 'Actualizar';
    cancelBtn.classList.remove('hidden');
    
    // Hacer scroll al formulario
    document.querySelector('.form-section').scrollIntoView({ behavior: 'smooth' });
  } catch (error) {
    console.error('Error:', error);
    alert('Error al cargar la cita para editar. Por favor, intenta de nuevo.');
  }
}

// Mostrar confirmación de eliminación
function showDeleteConfirmation(id) {
  citaAEliminar = id;
  deleteModal.classList.remove('hidden');
}

// Confirmar y ejecutar eliminación
async function confirmDelete() {
  if (!citaAEliminar) return;
  
  try {
    const response = await fetch(`${CITAS_API}/${citaAEliminar}`, {
      method: 'DELETE'
    });
    
    if (!response.ok) throw new Error('Error al eliminar la cita');
    
    // Actualizar la interfaz
    deleteModal.classList.add('hidden');
    citaAEliminar = null;
    fetchCitas();
    alert('Cita eliminada con éxito');
  } catch (error) {
    console.error('Error:', error);
    alert('Error al eliminar la cita. Por favor, intenta de nuevo.');
  }
}

// Filtrar citas por búsqueda
function filterCitas() {
  const searchTerm = searchInput.value.toLowerCase();
  
  if (!searchTerm) {
    renderCitas();
    return;
  }
  
  const filteredCitas = citas.filter(cita => {
    const clienteNombre = cita.cliente ? `${cita.cliente.nombre} ${cita.cliente.apellido}`.toLowerCase() : '';
    const estilistaNombre = cita.estilista ? `${cita.estilista.name} ${cita.estilista.lastname}`.toLowerCase() : '';
    const fecha = cita.fechaCita ? new Date(cita.fechaCita).toLocaleDateString('es-CO') : '';
    
    return clienteNombre.includes(searchTerm) || 
           estilistaNombre.includes(searchTerm) || 
           fecha.includes(searchTerm);
  });
  
  // Guardar las citas originales
  const originalCitas = [...citas];
  
  // Reemplazar temporalmente con las filtradas para renderizar
  citas = filteredCitas;
  renderCitas();
  
  // Restaurar las citas originales
  citas = originalCitas;
}

// Resetear formulario
function resetForm() {
  citaForm.reset();
  currentCitaId = null;
  formTitle.textContent = 'Agregar Nueva Cita';
  saveBtn.textContent = 'Guardar';
  cancelBtn.classList.add('hidden');
  
  // Desmarcar todos los servicios
  document.querySelectorAll('input[name="servicios"]').forEach(checkbox => {
    checkbox.checked = false;
  });
}