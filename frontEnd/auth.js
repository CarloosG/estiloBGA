function parseJwt(token) {
    try {
        const base64Payload = token.split('.')[1];
        const payload = atob(base64Payload);
        return JSON.parse(payload);
    } catch (e) {
        console.error("Error al decodificar el token", e);
        return null;
    }
}

function validarToken(rolEsperado) {
    const token = localStorage.getItem("token");
    if (!token) {
        alert("Sesión no iniciada. Redirigiendo...");
        window.location.href = "../index.html";
        return;
    }

    const payload = parseJwt(token);

    if (!payload) {
        alert("Token inválido. Redirigiendo...");
        window.location.href = "../index.html";
        return;
    }

    const now = Math.floor(Date.now() / 1000);
    if (payload.exp < now) {
        alert("Sesión expirada. Redirigiendo...");
        localStorage.removeItem("token");
        window.location.href = "../index.html";
        return;
    }

    const rol = payload.authorities[0].authority.toUpperCase(); // e.g. ROLE_CLIENTE

    if (rol !== rolEsperado.toUpperCase()) {
        alert("No tienes permisos para acceder a esta página.");
        window.location.href = "../index.html";
        return;
    }

    console.log("Token válido. Acceso permitido para:", rol);
}
