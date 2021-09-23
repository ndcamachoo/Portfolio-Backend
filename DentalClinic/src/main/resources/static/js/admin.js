/* =========== Pre-Load =============== */

let userName = document.querySelector(".username");
let fullname = document.querySelector(".fullname");
let TurnosTable = document.querySelector(".data");

let setRow = (field) => {
    return `
        <tr id="row">
            <td class='ID'>${field.id}</td>
            <td>${field.nombrePaciente}</td>
            <td>${field.nombreOdontologo}</td>
            <td><input type='date' value='${field.fechaTurno}' readonly></td>
            <td><input type='time' value='${field.horaTurno}' readonly></td>
            <td class='edit'><button class='subBtn'>Editar</button></td>
            <td class='delete'><button class='mainBtn'>Borrar</button></td>
        </tr>
    `;
}

let renderTable = () => {
    fetch(`http://localhost:8080/turnos`)
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

    let rows = document.querySelectorAll("#row");
    let insertTurno = document.querySelector(".insertTurno");
    let closePop = document.querySelector(".closepop");
    let formTurno = document.querySelector('.formTurno');
    let formUser = document.querySelector('.formUser');
    let optionPaciente = document.querySelector(".optionPaciente");
    let optionOdontologo = document.querySelector(".optionOdontologo");
    let dateTurno = document.querySelector(".dateTurno");
    let timeTurno = document.querySelector(".timeTurno");
    let editUser = document.querySelector(".editUser");
    let closeUser = document.querySelector(".closeUser");
    let userNameF = document.querySelector('.name');
    let userLastname = document.querySelector(".lastname");
    
    /* ================== Métodos ======================== */


    let setEditable = (array, state) => {

        array[0].contentEditable = state.toString();
        array[1].contentEditable = state.toString();
        array[2].firstElementChild.readOnly = !state;
        array[3].firstElementChild.readOnly = !state;

        if(state){
            array[0].style.color = "#41ffd4";
            array[1].style.color = "#41ffd4";
            array[2].firstElementChild.style.color = "#41ffd4";
            array[3].firstElementChild.style.color = "#41ffd4";
        }else{
            array[0].style.color = "white";
            array[1].style.color = "white";
            array[2].firstElementChild.style.color = "white";
            array[3].firstElementChild.style.color = "white";
        }

    }

    let getState = (array) => {
        return ({
            nombrePaciente : array[0].innerHTML,
            nombreOdontologo : array[1].innerHTML,
            fechaTurno : array[2].firstElementChild.value,
            horaTurno : array[3].firstElementChild.value,
        })
    }

    let setState = (array, prevState) => {

        array[0].innerHTML = prevState.nombrePaciente;
        array[1].innerHTML = prevState.nombreOdontologo;
        array[2].firstElementChild.value = prevState.fechaTurno;
        array[3].firstElementChild.value = prevState.horaTurno;

    }

    let settings = (method, body) => {
        return ({
            method: method,
            headers:{
                'Content-Type':'application/json'
             },
            body: JSON.stringify(body)
        })
    }
    
    let setAllData = (array, selector) => {

        selector.innerHTML = "";

        array.map(el => {
            
            let fullname =  `${el.usuario.apellido}, ${el.usuario.nombre}`;
            selector.innerHTML += `<option value='${fullname}'>${fullname}</option>`

        })

    }

    /* ================ Tabla ==================== */

    rows.forEach((row) => {

        let buttonEdit = row.querySelector(".edit").firstElementChild;
        let buttonDelete = row.querySelector(".delete").firstElementChild;
        let cells = row.querySelectorAll("td:not([class])")

        let temp = true;
        let bufferrState = [];

        /* ==================== Eventos ===================== */

        buttonEdit.addEventListener("click", ()=> {

            let ID = parseInt(row.querySelector(".ID").innerHTML);

            temp = !temp;

            if(!temp){
                setEditable(cells, true);
                buttonEdit.style.background = 'rgba(255,255,255,0.781)'; 
                bufferrState.push(getState(cells))

            }else{
                setEditable(cells, false);
                buttonEdit.style.background = 'linear-gradient(to right, #ffb941, #ffaa2b)';
                bufferrState.push(getState(cells))

                let [prevState, state] = bufferrState;

                if (confirm("¿Quieres actualizar este turno?")) {
                    
                    let infoBody = {id: ID}
                    infoBody = Object.assign(infoBody, state);

                    console.log(infoBody)
                    
                    fetch("http://localhost:8080/turnos/update",settings("PUT", infoBody))
                    .then(response => {
                        if(response.status == 404){
                            setState(cells, prevState)
                            alert("El Odontologo/Paciente no existe en la base de datos, por favor, cámbielo");
                            throw new Error("Odontologo/Paciente no existente");
                        }
                        return response.text();
                    })
                    .then(data => alert("Actualizado! ID: " + ID))
                    .catch(error => console.log(error))
                    

                }else{
                    alert("No se actualiza, seteado al estado anterior ID: " + ID)
                    console.log(prevState)
                    setState(cells, prevState)
                }

                bufferrState.length == 2? bufferrState = [] : 0;
            }
    })

        buttonDelete.addEventListener('click', ()=> {

            let ID = parseInt(row.querySelector(".ID").innerHTML);

            if (confirm("¿Quieres eliminar este turno?")) {

                fetch(`http://localhost:8080/turnos/delete/${ID}`,settings("DELETE", ""))
                .then(response =>  response.text())
                .then(data => {
                    alert("Eliminado! ID: " + ID);
                    window.location.reload();
                })
                .catch(error => console.log(error))

                
            }else{
                alert("No se elimina ID: " + ID)
            }
        })

    })

    insertTurno.addEventListener("click", ()=> {
        let overlay = document.querySelector("#overlay");
        let pop = document.querySelector(".pop");

        overlay.classList.toggle("active");
        pop.classList.toggle("active");

        fetch("http://localhost:8080/odontologos")
        .then(response => response.json())
        .then(data => setAllData(data,optionOdontologo))
        .catch(error => console.log(error))

        fetch("http://localhost:8080/pacientes")
        .then(response => response.json())
        .then(data => setAllData(data,optionPaciente))
        .catch(error => console.log(error))

        let today = new Date();
        let year = today.getFullYear()
        let month = today.getMonth()+1
        let day = today.getDate()
        let hour = today.getHours();
        let minutes = today.getMinutes();
        let fecha = `${year}-${month.toString.length == 1? "0"+month: month}-${day}`;
        let hora = `${hour}:${minutes}`;   
        
        dateTurno.value = fecha;
        timeTurno.value = hora;
    })

    closePop.addEventListener('click', ()=> {
        let overlay = document.querySelector("#overlay");
        let pop = document.querySelector(".pop");
        overlay.classList.toggle("active");
        pop.classList.toggle("active");
    })

    formTurno.addEventListener("submit", (e) => {
        e.preventDefault();

        let infoTurno = {
            nombrePaciente: optionPaciente.value,
            nombreOdontologo: optionOdontologo.value,
            fechaTurno: dateTurno.value,
            horaTurno: timeTurno.value
        }

        fetch(`http://localhost:8080/turnos/save`,settings("POST", infoTurno))
        .then(response =>  response.json())
        .then(data => {
            alert("Turno generado ID: " + data.id);
            window.location.reload();
        })
        .catch(error => console.log(error))

    })
    
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

