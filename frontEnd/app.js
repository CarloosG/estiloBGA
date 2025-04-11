const inputs = document.querySelectorAll(".input-field");
const toggle_btn = document.querySelectorAll(".toggle");
const main = document.querySelector("main");
const bullets = document.querySelectorAll(".bullets span");
const images = document.querySelectorAll(".image");

inputs.forEach((inp) => {
  inp.addEventListener("focus", () => {
    inp.classList.add("active");
  });
  inp.addEventListener("blur", () => {
    if (inp.value != "") return;
    inp.classList.remove("active");
  });
});

toggle_btn.forEach((btn) => {
  btn.addEventListener("click", () => {
    main.classList.toggle("sign-up-mode");
  });
});

function moveSlider() {
  let index = this.dataset.value;

  let currentImage = document.querySelector(`.img-${index}`);
  images.forEach((img) => img.classList.remove("show"));
  currentImage.classList.add("show");

  const textSlider = document.querySelector(".text-group");
  textSlider.style.transform = `translateY(${-(index - 1) * 2.2}rem)`;

  bullets.forEach((bull) => bull.classList.remove("active"));
  this.classList.add("active");
}

bullets.forEach((bullet) => {
  bullet.addEventListener("click", moveSlider);
});
// Validación de formulario de inicio de sesión
document.getElementById("loginForm").addEventListener("submit", async function (e) {
  e.preventDefault();

  const correo = this.correo.value;
  const password = this.password.value;

  try {
    const response = await fetch("http://localhost:8094/auth/login", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({ correo, password }),
    });

    if (!response.ok) throw new Error("Credenciales inválidas");

    const data = await response.json();
    const token = data.token;
    
    localStorage.setItem("token", token);

    // decodificar el token y redirigir según el rol
    const payload = parseJwt(token);
    console.log("Payload JWT:", payload);
    const rol = payload.authorities[0].authority.toUpperCase(); // viene como "ROLE_CLIENTE", etc.

    if (rol === "ROLE_CLIENTE") {
      window.location.href = "pages/cliente.html";
    } else if (rol === "ROLE_ESTILISTA") {
      window.location.href = "pages/estilista.html";
    } else if (rol === "ROLE_ADMIN") {
      window.location.href = "pages/admin.html";
    } else {
      alert("Rol desconocido");
    }

  } catch (error) {
    alert("Error al iniciar sesión: " + error.message);
  }
});


function parseJwt(token) {
  try {
    const base64Payload = token.split('.')[1];
    const payload = atob(base64Payload);
    return JSON.parse(payload);
  } catch (e) {
    console.error("Error al decodificar JWT", e);
    return null;
  }
}

