let userName = document.querySelector(".username");
let fullname = document.querySelector(".fullname");
let TurnosTable = document.querySelector(".data");
let Type = document.querySelector("main section small").innerHTML == "Odontologo"? "o" : "p";

let setRow = (field) => {
    return `
        <tr id="row">
            <td class='ID'>${field.id}</td>
            <td>${field.nombrePaciente}</td>
            <td>${field.nombreOdontologo}</td>
            <td><input type='date' value='${field.fechaTurno}' readonly></td>
            <td><input type='time' value='${field.horaTurno}' readonly></td>
        </tr>
    `;
}

let renderTable = () => {
    fetch(`http://localhost:8080/turnos/search${Type}/${userName.innerHTML}`)
    .then(response => response.json())
    .then(data => {
        TurnosTable.innerHTML = "";
        data.forEach(turno => {
            TurnosTable.innerHTML += setRow(turno);
        });
    })
    .catch(error => console.log(error))
}

let setFullName = (username) => {
    fetch(`http://localhost:8080/user/${username}`)
    .then(response => response.json())
    .then(data => fullname.innerHTML = data.fullname)
    .catch(error => console.log(error))
}

setFullName(userName.innerHTML)

renderTable();

window.addEventListener("load", ()=> {

    /* ================== Selectores ======================= */

    let formUser = document.querySelector('.formUser');
    let editUser = document.querySelector(".editUser");
    let closeUser = document.querySelector(".closeUser");
    let userNameF = document.querySelector('.name');
    let userLastname = document.querySelector(".lastname");

    /* ================== Métodos ======================== */

    let settings = (method, body) => {
        return ({
            method: method,
            headers:{
                'Content-Type':'application/json'
             },
            body: JSON.stringify(body)
        })
    }
    
    /* ==================== Eventos ===================== */

    editUser.addEventListener('click', ()=>{
        let overlay = document.querySelector("#overlay");
        let user = document.querySelector(".user");

        overlay.classList.toggle("active");
        user.classList.toggle("active");
    })
    
    closeUser.addEventListener("click", ()=> {
        let overlay = document.querySelector("#overlay");
        let user = document.querySelector(".user");
        overlay.classList.toggle("active");
        user.classList.toggle("active");
    })

    formUser.addEventListener('submit', (e)=> {

        e.preventDefault();

        let infoUser = {
            fullname: `${userLastname.value}, ${userNameF.value}`,
            user: userName.innerHTML
        }

        fetch(`http://localhost:8080/user/update`,settings("PUT", infoUser))
        .then(response =>  response.text())
        .then(data => {
            alert("Usuario actualizado");
            window.location.reload();
        })
        .catch(error => console.log(error))

    })


})