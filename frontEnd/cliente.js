function cerrarSesion() {
    localStorage.clear();
    window.location.href = "../index.html";
}

const token = localStorage.getItem("token");
const payload = parseJwt(token);
const correo = payload.sub;
const API_URL = "http://localhost:8094/api";

document.addEventListener("DOMContentLoaded", () => {
    mostrarPerfil();
    cargarEstilistas();
    //cargarServicios();
    //cargarCitas();

    //document.getElementById("formNuevaCita").addEventListener("submit", agendarCita);
});

function mostrarPerfil() {
    const perfilDiv = document.getElementById("perfil");
    const rol = payload.authorities[0].authority;
    perfilDiv.innerHTML = `<p><strong>Correo:</strong> ${correo} | <strong>Rol:</strong> ${rol}</p>`;
}

function cargarEstilistas() {

    fetch("http://localhost:8094/api/estilistas/list", {
        method: "GET",
        headers: {
            "Authorization": "Bearer <TU_TOKEN>",
        },
        credentials: "include" // si estás usando allowCredentials
    })
        .then(res => res.json())
        .then(console.log)
        .catch(console.error);
    // fetch(`${API_URL}/estilistas/list`, {
    //     headers: { Authorization: "Bearer " + token },
    // })
    //     .then(res => { res.json(); console.log(res); })
    //     .then(data => {
    //         const select = document.getElementById("selectEstilista");
    //         select.innerHTML = data.map(e => `<option value="${e.estilista_id}">${e.nombres}</option>`).join("");
    //     })
}

function cargarServicios() {
    fetch(`${API_URL}/servicios/list`, {
        headers: { Authorization: "Bearer " + token },
    })
        .then(res => res.json())
        .then(data => {
            const select = document.getElementById("selectServicio");
            select.innerHTML = data.map(s => `<option value="${s.servicioId}">${s.nombreServicio}</option>`).join("");
        });
}

function cargarCitas() {
    fetch(`${API_URL}/citas/cliente/${correo}`, {
        headers: { Authorization: "Bearer " + token },
    })
        .then(res => res.json())
        .then(data => {
            const lista = document.getElementById("listaCitas");
            if (data.length === 0) {
                lista.textContent = "No tienes citas programadas.";
            } else {
                lista.innerHTML = "<ul>" + data.map(c => `<li>${c.fechaCita} con ${c.estilistaNombre}</li>`).join("") + "</ul>";
            }
        });
}

function agendarCita(e) {
    e.preventDefault();
    const fecha = e.target.fecha.value;
    const estilistaId = e.target.estilista.value;
    const servicioId = e.target.servicio.value;

    fetch("http://localhost:8094/citas", {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
            Authorization: "Bearer " + token,
        },
        body: JSON.stringify({
            correoCliente: correo,
            estilistaId,
            fechaCita: fecha,
            servicioId
        }),
    })
        .then(res => {
            if (!res.ok) throw new Error("Error al agendar cita");
            return res.json();
        })
        .then(() => {
            alert("Cita agendada exitosamente");
            cargarCitas(); // recarga citas
        })
        .catch(err => alert(err.message));
}
