async function app() {

    const response = await fetch('api/admin')

    if (response.ok) {
        let json = await response.json()
    } else {
        alert("Ошибка" + response.status)
    }

    function generateTable(data) {

        const tbl = document.getElementById("usersTable")
        tbl.innerHTML = ''
        data.forEach(({id, name, age, surname, username, roles}) => {
                let userRoles = ''
                roles.forEach((role) => {
                    userRoles = userRoles + role.name + ' '
                })
                const element = document.createElement('tr')
                element.innerHTML = `
            <th scope="row">${id}</th>
            <td>${name}</td>
            <td>${surname}</td>
            <td>${age}</td>
            <td>${username}</td>
            <td>${userRoles}</td>
            <td>
                <button type="button" class="btn btn-info text-white" data-bs-userId=${id}
                    data-bs-userName=${name} data-bs-userSurname=${surname} data-bs-userAge=${age}
                    data-bs-userEmail=${username} data-bs-toggle="modal"
                    data-bs-target="#ModalEdit">Edit</button>
            </td>
            <td>
                <button type="button" class="btn btn-danger" data-bs-userId=${id}
                    data-bs-userName=${name} data-bs-userSurname=${surname} data-bs-userAge=${age}
                    data-bs-userEmail=${username} data-bs-toggle="modal"
                    data-bs-target="#ModalDelete">Delete</button>
            </td>            
            `
                tbl.append(element)


            }
        )
    }
}

//Изменение юзера
const formEdit = document.getElementById("formEdit");
formEdit.addEventListener('submit', e => {
    e.preventDefault();

    const formData = new FormData(formEdit);
    const object = {
        roles: []
    };

    formData.forEach((value, key) => {
        console.log(key)
        if (key === "rolesId") {

            const roleId = value.split(" ")[0];
            const roleName = value.split(" ")[1];
            const role = {
                id: roleId,
                name: roleName
            };

            object.roles.push(role);
        } else {
            object[key] = value;
        }
    });
    fetch("api/", {
        method: "PUT",
        headers: {
            "Content-type": "application/json"
        },
        body: JSON.stringify(object)
    })
        .then(() => app());
    $("#ModalEdit").modal("hide");
    formEdit.reset();
})

//Модульное окно Edit
const editModal = document.getElementById('ModalEdit')
editModal.addEventListener('show_bs_modal', event => {

    const button = event.relatedTarget

    const userId = button.getAttribute('data_bs_userId')
    const userName = button.getAttribute('data_bs_userName')
    const userSurname = button.getAttribute('data_bs_userSurname')
    const userAge = button.getAttribute('data_bs_userAge')
    const userEmail = button.getAttribute('data_bs_userEmail')

    const modalUserId = editModal.querySelector('#userId')
    const modalUserName = editModal.querySelector('#userName')
    const modalUserSurname = editModal.querySelector('#userSurname')
    const modalUserAge = editModal.querySelector('#userAge')
    const modalUserEmail = editModal.querySelector('#userEmail')

    modalUserId.value = userId
    modalUserName.value = userName
    modalUserSurname.value = userSurname
    modalUserAge.value = userAge
    modalUserEmail.value = userEmail
})

//Удаление Юзера

const formDelete = document.getElementById('formDelete')
formDelete.addEventListener('submit', e => {
    e.preventDefault()
    const formData = new FormData(formDelete)
    fetch('api/' + formData.get('id'), {
        method: 'DELETE'
    }).then(() => app())
    $('#ModalDelete').modal('hide')
    formDelete.reset()
})

//Модульное окно Delete

const DeleteModel = document.getElementById('ModalDelete')
DeleteModel.addEventListener('show.bs.modal', event => {
    const Dbutton = event.relatedTarget

    const DuserId = Dbutton.getAttribute('data-bs-userId')
    const DuserName = Dbutton.getAttribute('data-bs-userName')
    const DuserSurname = Dbutton.getAttribute('data-bs-userSurname')
    const DuserAge = Dbutton.getAttribute('data-bs-userAge')
    const DuserEmail = Dbutton.getAttribute('data-bs-userEmail')

    const DmodaluserId = DeleteModel.querySelector('#userIdDelete')
    const DmodaluserName = DeleteModel.querySelector('#userNameDelete')
    const DmodaluserSurname = DeleteModel.querySelector('#userSurnameDelete')
    const DmodaluserAge = DeleteModel.querySelector('#userAgeDelete')
    const DmodaluserEmail = DeleteModel.querySelector('#userEmailDelete')

    DmodaluserId.value = DuserId
    DmodaluserName.value = DuserName
    DmodaluserSurname.value = DuserSurname
    DmodaluserAge.value = DuserAge
    DmodaluserEmail.value = DuserEmail
})
//Добавление юзера

const addForm = document.getElementById('addForm')
addForm.addEventListener('submit', (e) => {
    e.preventDefault()
    const formData = new FormData(addForm)
    const object = {roles: []}

    formData.forEach((value, key) => {
        if (key === 'rolesId') {
            const roleId = value.split((" ")[0])
            const roleName = value.split((" ")[1])
            const role = {
                id: roleId,
                name: roleName
            }
            object.roles.push(role)
        } else {
            object[key] = value
        }
    })
    fetch('api/', {
        method: 'POST',
        headers: {'Content-type': 'application/json'},
        body: JSON.stringify(object)
    })
        .then(() => app())
        .then(() => addForm.reset())
    return show('Page1', 'Page2')
})




























