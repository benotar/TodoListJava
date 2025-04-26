console.log("todo-update")

const forms = document.querySelectorAll('#form-container form');

forms.forEach(function (form) {
    form.addEventListener('submit', function (event) {
        event.preventDefault()
        clientUpdate(form)
    })
})

const clientUpdate = async (form) => {
    const formData = new FormData(form);

    const completed = formData.get("completed") == null ? false : true;

    const data = {
        id: Number(formData.get("id")),
        title: formData.get("title"),
        description: formData.get("description"),
        completed: completed,
        categoryId: Number(formData.get("categoryId")),
        tagId: Number(formData.get("tagId"))
    };

    const json = JSON.stringify(data);
    console.log(json);

    try {
        const response = await fetch("/rest/todo-update-form", {
            method: "POST",
            headers: {
                'Content-Type': 'application/json',
                'X-CSRF-Token': formData.get('_csrf')
            },
            body: json
        });

        if (response.ok) {
            alert("Saved!");
        } else {
            alert("Error");
        }

    } catch (error) {
        console.log(error);
    }

    // fetch('/rest/todo-update-form', {
    //     method: 'POST',
    //     headers: {
    //         'Content-Type': 'application/json',
    //         'X-CSRF-Token': formData.get('_csrf')
    //     },
    //     body: json
    // })
    //     .then(data => {
    //         console.log(data)
    //         if (data.ok) alert('Saved!')
    //         else alert('Error')
    //     })
    //     .catch(error => {
    //         console.log(error)
    //     });
}