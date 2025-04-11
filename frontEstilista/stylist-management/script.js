// URL base para las API de estilistas
const API_URL = "http://localhost:8094/api/estilistas";

// Elementos del DOM
const stylistForm = document.getElementById("stylist-form");
const formTitle = document.getElementById("form-title");
const stylistIdInput = document.getElementById("stylist-id");
const nameInput = document.getElementById("name");
const lastnameInput = document.getElementById("lastname");
const specialtyInput = document.getElementById("specialty");
const experienceInput = document.getElementById("experience");
const phoneInput = document.getElementById("phone");
const cedulaciudadaniaInput = document.getElementById("id")
const emailInput = document.getElementById("email");
const availabilityInput = document.getElementById("availability");
const saveBtn = document.getElementById("save-btn");
const cancelBtn = document.getElementById("cancel-btn");
const stylistsList = document.getElementById("stylists-list");
const searchInput = document.getElementById("search-input");
const noStylistsMessage = document.getElementById("no-stylists-message");
const deleteModal = document.getElementById("delete-modal");
const confirmDeleteBtn = document.getElementById("confirm-delete");
const cancelDeleteBtn = document.getElementById("cancel-delete");

// Variables globales
let stylists = [];
let currentStylistId = null;
let stylistToDeleteId = null;

// Inicialización
document.addEventListener("DOMContentLoaded", () => {
  fetchStylists();

  // Event listeners
  stylistForm.addEventListener("submit", handleFormSubmit);
  cancelBtn.addEventListener("click", resetForm);
  searchInput.addEventListener("input", handleSearch);
  confirmDeleteBtn.addEventListener("click", confirmDelete);
  cancelDeleteBtn.addEventListener("click", () => {
    deleteModal.classList.add("hidden");
    stylistToDeleteId = null;
  });
});

// Obtener la lista de estilistas desde el backend
async function fetchStylists() {
  try {
    const response = await fetch(`${API_URL}/list`);
    if (!response.ok) {
      throw new Error("No se pudo obtener la lista de estilistas");
    }
    stylists = await response.json();
    console.log("Estilistas cargados:", stylists); // Para depuración
    renderStylistsList();
  } catch (error) {
    console.error("Error al cargar estilistas:", error);
    alert("Error al cargar los estilistas. Por favor, intente de nuevo más tarde.");
  }
}

// Renderizar la lista de estilistas
function renderStylistsList(filteredStylists = null) {
  const stylistsToRender = filteredStylists || stylists;

  if (stylistsToRender.length === 0) {
    stylistsList.innerHTML = "";
    noStylistsMessage.classList.remove("hidden");
    return;
  }

  noStylistsMessage.classList.add("hidden");

  stylistsList.innerHTML = stylistsToRender
    .map(
      (stylist) => `
    <tr data-id="${stylist.id}">
      <td>${stylist.usuario.nombres || 'No especificada'}</td> 
      <td>${stylist.usuario.apellidos || 'No especificada'}</td> 
      <td>${stylist.especializacion || 'No especificada'}</td>
      <td>${stylist.aniosExperiencia || 0} años</td>
      <td class="action-buttons">
        <button class="edit-btn" onclick="editStylist(${stylist.id})">Editar</button>
        <button class="delete-btn" onclick="deleteStylist(${stylist.id})">Eliminar</button>
      </td>
    </tr>
  `
    )
    .join("");
}

// Función auxiliar para mostrar la disponibilidad (ya que parece que no existe en el modelo actual)
function getAvailabilityText(stylist) {
  // Como no hay un campo de disponibilidad directo en el modelo,
  // podemos usar una opción predeterminada o derivarla de otra información
  return "Tiempo completo"; // Valor por defecto
}

// Manejar envío del formulario (crear o actualizar)
async function handleFormSubmit(e) {
  e.preventDefault();


  // Crear objeto de usuario
  const userData = {
    nombres: nameInput.value,
    apellidos: lastnameInput.value,
    correoElectronico: emailInput.value.trim(),
    celular: phoneInput.value.trim(),
    numeroDocumento: cedulaciudadaniaInput.value,
    rol: "estilista",
    // Estos campos podrían ser necesarios según tu backend:
    password: "tempPassword123", // Contraseña temporal o campo adicional en el formulario
    fechaRegistro: new Date().toISOString().split('T')[0] // Fecha actual en formato YYYY-MM-DD
  };

  // Crear objeto de estilista
  const stylistData = {
    aniosExperiencia: Number.parseInt(experienceInput.value),
    especializacion: specialtyInput.value.trim()
  };

  try {
    if (currentStylistId) {
      // Para actualización, necesitamos el ID del estilista existente
      stylistData.id = currentStylistId;
      
      // También necesitamos el ID del usuario si lo tenemos
      const existingEstilista = stylists.find(s => s.id == currentStylistId);
      if (existingEstilista && existingEstilista.usuario) {
        userData.id = existingEstilista.usuario.id;
      }

      // Objeto completo para la actualización
      const completeData = {
        ...stylistData,
        usuario: userData
      };

      await updateStylist(currentStylistId, completeData);
    } else {
      // Objeto completo para la creación
      const completeData = {
        ...stylistData,
        usuario: userData
      };

      await createStylist(completeData);
    }

    resetForm();
    fetchStylists(); // Recargar la lista después de crear/actualizar
  } catch (error) {
    console.error("Error al guardar el estilista:", error);
    alert("Error al guardar el estilista. Por favor, intente de nuevo.");
  }
}

// Crear un nuevo estilista
async function createStylist(stylistData) {
  const response = await fetch(`${API_URL}/`, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(stylistData),
  });

  if (!response.ok) {
    throw new Error("No se pudo crear el estilista");
  }

  return await response.json();
}

// Actualizar un estilista existente
async function updateStylist(id, stylistData) {
  const response = await fetch(`${API_URL}/`, {
    method: "PUT",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(stylistData),
  });

  if (!response.ok) {
    throw new Error("No se pudo actualizar el estilista");
  }

  return await response.json();
}

// Editar un estilista (cargar datos en el formulario)
async function editStylist(id) {
  try {
    const response = await fetch(`${API_URL}/list/${id}`);
    if (!response.ok) {
      throw new Error("No se pudo obtener el estilista");
    }
    
    const stylist = await response.json();
    
    if (stylist) {
      // Cambiar el modo del formulario a edición
      formTitle.textContent = "Editar Estilista";
      cancelBtn.classList.remove("hidden");
      currentStylistId = stylist.id;

      // Llenar el formulario con los datos del estilista
      stylistIdInput.value = stylist.id;
      
      // Si el estilista tiene un usuario asociado, completar los campos correspondientes
      if (stylist.usuario) {
        // Combinar nombres y apellidos para el campo de nombre completo
        nameInput.value = stylist.usuario.nombres || '';
        lastnameInput.value = stylist.usuario.apellidos || '';
        
        emailInput.value = stylist.usuario.correoElectronico || '';
        phoneInput.value = stylist.usuario.celular || '';
        cedulaciudadaniaInput.value = stylist.usuario.numeroDocumento || '';
      }
      
      specialtyInput.value = stylist.especializacion || '';
      experienceInput.value = stylist.aniosExperiencia || 0;

      // Hacer scroll al formulario
      document.querySelector(".form-section").scrollIntoView({ behavior: "smooth" });
    }
  } catch (error) {
    console.error("Error al cargar el estilista para editar:", error);
    alert("Error al cargar el estilista. Por favor, intente de nuevo.");
  }
}

// Iniciar proceso de eliminación (mostrar modal)
function deleteStylist(id) {
  stylistToDeleteId = id;
  deleteModal.classList.remove("hidden");
}

// Confirmar eliminación
async function confirmDelete() {
  if (stylistToDeleteId) {
    try {
      const response = await fetch(`${API_URL}/${stylistToDeleteId}`, {
        method: "DELETE",
      });
      
      if (!response.ok) {
        throw new Error("No se pudo eliminar el estilista");
      }
      
      deleteModal.classList.add("hidden");
      stylistToDeleteId = null;
      fetchStylists(); // Recargar la lista después de eliminar
    } catch (error) {
      console.error("Error al eliminar el estilista:", error);
      alert("Error al eliminar el estilista. Por favor, intente de nuevo.");
      deleteModal.classList.add("hidden");
    }
  }
}

// Buscar estilistas
function handleSearch() {
  const searchTerm = searchInput.value.toLowerCase().trim();

  if (searchTerm === "") {
    renderStylistsList();
    return;
  }

  const filteredStylists = stylists.filter(
    (stylist) =>
      (stylist.usuario && stylist.usuario.nombres && stylist.usuario.nombres.toLowerCase().includes(searchTerm)) ||
      (stylist.usuario && stylist.usuario.apellidos && stylist.usuario.apellidos.toLowerCase().includes(searchTerm)) ||
      (stylist.especializacion && stylist.especializacion.toLowerCase().includes(searchTerm)) ||
      (stylist.usuario && stylist.usuario.correoElectronico && stylist.usuario.correoElectronico.toLowerCase().includes(searchTerm))
  );

  renderStylistsList(filteredStylists);
}

// Resetear el formulario
function resetForm() {
  stylistForm.reset();
  formTitle.textContent = "Agregar Nuevo Estilista";
  cancelBtn.classList.add("hidden");
  currentStylistId = null;
  stylistIdInput.value = "";
}

// Exponer funciones al ámbito global para los event handlers en línea
window.editStylist = editStylist;
window.deleteStylist = deleteStylist;