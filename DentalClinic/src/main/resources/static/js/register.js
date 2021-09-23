window.addEventListener("load", ()=> {

    /* ================ Elementos =====================  */
    
    let esOdontologo = document.querySelector(".isOdontologo");
    let entityChar = document.querySelector(".entityChar");
    let inputDate = document.querySelector("input[type='date']");
    let form = document.querySelector("form");
    
    /* ============ Datos Formulario ============  */
    
    let user = document.querySelector(".user");
    let pass = document.querySelector(".pass");
    let nameU = document.querySelector(".name");
    let lastname = document.querySelector(".lastname");
    let calle = document.querySelector(".calle");
    let numerocalle = document.querySelector(".numerocalle");
    let localidad = document.querySelector(".localidad");
    let provincia = document.querySelector(".provincia");
    let isAdmin;
    let DNI = document.querySelector(".DNI");
    let matricula;
    
    
    
    /* ============= Error msg ================= */
    
    let errorU = document.querySelector(".errorU");
    let errorP = document.querySelector(".errorP");
    let errorN = document.querySelector(".errorN");
    let errorL = document.querySelector(".errorL");
    let errorC = document.querySelector(".errorC");
    let errorNC = document.querySelector(".errorNC");
    let errorLO = document.querySelector(".errorLO");
    let errorPV = document.querySelector(".errorPV");
    let errorDNI = document.querySelector(".errorDNI");
    let errorDate = document.querySelector(".errorDate");
    let errorMAT;
    
    
    /* ================= Fecha =====================  */
    
    let today = new Date();
    let year = today.getFullYear()
    let month = today.getMonth()+1
    let day = today.getDate()
    let fecha = `${year}-${month.toString.length == 1? "0"+month: month}-${day}`   
    
    inputDate.value= fecha;
    
    
    /* ================ Métodos ======================= */
    
    let isPastDate = (date) => {
        let dateSplit = date.split("-").join("");
        let nowDateSplit = fecha.split("-").join("");
        return dateSplit < nowDateSplit
    }
    
    let validateForm = (errorModel, condition, msg) => {
    
        let error= false;
    
        if(condition){
            errorModel.innerHTML = "";
            error = false;
        }else{
            errorModel.innerHTML = msg; 
            error = true;
        }
    
        return !error;
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
    
    
    /* ================= Eventos ==================== */
    
    esOdontologo.addEventListener("click",(e)=>{
        if(esOdontologo.checked){
    
            entityChar.innerHTML = `
            
            <div class="box">
                <p>Número de matrícula</p>
                <div>
                    <input class='matricula' type="number" placeholder="Ingresa tu numero de matrícula">
                </div>
                <span class="errorMAT"></span>
            </div>
            
            <label><input type="checkbox" class="isAdmin" id="cbox2" value="isAdmin">  ¿Eres administrador?</label>
            
            `
            matricula = document.querySelector(".matricula");
            errorMAT = document.querySelector(".errorMAT");
            isAdmin = document.querySelector(".isAdmin");
    
        }else{
    
            entityChar.innerHTML = `
            
            <div class="box">
                <p>DNI</p>
                <div>
                    <input class="DNI" type="number" placeholder="Ingresa tu DNI">
                </div>
                <span class="errorDNI"></span>
            </div>
    
            <div class="box">
                <p>Fecha de ingreso</p>
                <div>
                    <input type="date" placeholder="Ingresa tu numero de calle" value="${fecha}">
                </div>
                <span class="errorDate"></span>
            </div>
            
            `
            
        }
    })
    
    form.addEventListener('submit',(e)=>{
    
        e.preventDefault();
    
        let va =validateForm(errorU, user.value.length >= 2 && user.value.length <= 10, "El usuario debe contener de 2 a 10 caracteres");
        let vb =validateForm(errorP, pass.value.length >= 2 && pass.value.length <= 10, "El usuario debe contener de 2 a 10 caracteres");
        let vc =validateForm(errorN, nameU.value.length >= 2 && nameU.value.length <= 20, "El nombre debe contener de 2 a 20 caracteres");
        let vd =validateForm(errorL, lastname.value.length >= 2 && lastname.value.length <= 20, "El apellido debe contener de 2 a 20 caracteres");
        let ve =validateForm(errorC, calle.value.length >= 2 && calle.value.length <= 10, "La calle debe contener de 2 a 10 caracteres");
        let vf =validateForm(errorNC, numerocalle.value.length >= 2 && numerocalle.value.length <= 10,"El numero debe contener de 2 a 10 caracteres");
        let vg =validateForm(errorLO, localidad.value.length >= 2 && localidad.value.length <= 20, "La localidad debe contener de 2 a 20 caracteres");
        let vh =validateForm(errorPV, provincia.value.length >= 2 && provincia.value.length <= 20, "La localidad debe contener de 2 a 20 caracteres" );
        let vi;
        let vj;
    
        if(esOdontologo.checked){
            vi = validateForm(errorMAT, matricula.value.toString().length == 9, "El número de matrícula debe contiener 9 numeros");
        }else{
            vj = validateForm(errorDNI, DNI.value.toString().length == 9, "El DNI debe contiener 9 numeros");
        }  
        
        isPastDate(inputDate.value) ? errorDate.innerHTML="La fecha no puede estar en el pasado" : errorDate.innerHTML="";
    
        if(va && vb && vc && vd && ve && vf && vg && vh && (vi || vj)){
            
            if(esOdontologo.checked){
    
                let infoO  = {
                    "numeroMatricula": matricula.value,
                    "admin": isAdmin.checked,
                    "usuario": {
                        "nombre": nameU.value,
                        "apellido": lastname.value,
                        "user": user.value.toLowerCase(),
                        "password": pass.value
                    },
                    "domicilio": {
                        "calle": calle.value,
                        "numero": numerocalle.value,
                        "localidad": localidad.value,
                        "provincia": provincia.value
                    }
                }
    
                fetch("http://localhost:8080/odontologos/save", settings("POST", infoO))
                .then(response => {
                    if(response.status == 409){
                        alert("El Usuario/Número de matrícula ya existe, por favor, cámbielo");
                        throw new Error("Usuario/Número de matrícula ya existente");
                    }
                    return response.json();
                })
                .then(data => {alert("Odontologo correctamente registrado"); window.location.pathname= '/'})
                .catch(error => console.log(error))
    
            }else{
    
                let infoP = {
                    "dni": DNI.value,
                    "fechaIngreso": inputDate.value,
                    "usuario": {
                        "nombre": nameU.value,
                        "apellido": lastname.value,
                        "user": user.value.toLowerCase(),
                        "password": pass.value
                    },
                    "domicilio": {
                        "calle": calle.value,
                        "numero": numerocalle.value,
                        "localidad": localidad.value,
                        "provincia": provincia.value
                    }
                }
    
                fetch("http://localhost:8080/pacientes/save", settings("POST", infoP))
                .then(response => {
                    if(response.status == 409){
                        alert("El Usuario/DNI ya existe, por favor, cámbielo");
                        throw new Error("Usuario/DNI ya existente");
                    }
                    return response.json();
                })
                .then(data => {alert("Paciente correctamente registrado"); window.location.pathname= '/'})
                .catch(error => console.log(error))
            }
    
        }
    
       
        
    })
    
    
    
    
    
    
    

})

