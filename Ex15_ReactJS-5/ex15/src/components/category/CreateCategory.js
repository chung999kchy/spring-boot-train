import React, { useContext, useState } from 'react';
import { useHistory } from 'react-router';
import categoryApi from '../../api/categoryApi';
import ToastContext from '../../context/ToastContext';

function CreateCategory(props) {
    const history = useHistory();
    const { setToastList } = useContext(ToastContext);
    const [mess, setMess] = useState({
        "name": "",
        "code": "",
        "description": ""
    })
    const [errors, setErrors] = useState({});

    const handleName = (e) => {
        setMess({
            ...mess,
            "name": e.target.value
        })
    }

    const handleCode = (e) => {
        setMess({
            ...mess,
            "code": e.target.value
        })
    }
    const handleDescription = (e) => {
        setMess({
            ...mess,
            "description": e.target.value
        })
    }

    const handleSubmit = async (e) => {
        e.preventDefault();
        setErrors(validate(mess));
        if (Object.keys(errors).length === 0) {
            try {
                const data = JSON.stringify(mess);
                const response = await categoryApi.create(data);
                if (response.data) {
                    setToastList([{ title: "Success", message: `Success created new category`, type: 'success' }])

                }

                else {
                    setToastList([{ title: "Error", message: `Error created new category`, type: 'error' }])

                }
            } catch (error) {
                console.log(error);
            }
        }
    }

    function validate(mess = {
        name: '',
        code: '',
        description: ''
    }) {
        const errors = {};
        if (mess.name.length < 3 || mess.name.length > 256) errors.name = "Name must be between 3 and 255 characters long"
        if (mess.code.length < 2 || mess.code.length > 6) errors.code = "Code must be between 2 and 6 characters long"
        if (mess.description.length < 1) errors.description = "Description not be null"
        return errors;
    }

    function handleFocus() {
        setErrors({});
    }


    return (
        <div className="category">
            <h2>Create a new category</h2>
            <button className="button-back" onClick={history.goBack}>
                <i className="fas fa-backward"></i>  Back
            </button>
            <form className="new-form" id="form-create__category" onSubmit={handleSubmit}>
                <div className={errors.name ? "form-group invalid" : "form-group"}>
                    <label htmlFor="name" className="form-label">Name</label>
                    <input id="name" className="form-control" name="name" type="text" onChange={handleName} onFocus={handleFocus} />
                    <span className="form-message">{errors.name}</span>
                </div>

                <div className={errors.code ? "form-group invalid" : "form-group"}>
                    <label htmlFor="code" className="form-label">Code</label>
                    <input id="code" className="form-control" name="code" type="text" onChange={handleCode} onFocus={handleFocus} />
                    <span className="form-message">{errors.code}</span>
                </div>
                <div className={errors.description ? "form-group invalid" : "form-group"}>
                    <label htmlFor="description" className="form-label">Description</label>
                    <input id="description" className="form-control" name="description" type="text" onChange={handleDescription} onFocus={handleFocus} />
                    <span className="form-message">{errors.description}</span>
                </div>
                <button className="form-submit">Submit</button>
            </form>
        </div>

    );
}

export default CreateCategory;