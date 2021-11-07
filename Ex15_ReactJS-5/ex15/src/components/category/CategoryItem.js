/* eslint-disable react-hooks/exhaustive-deps */
import React, { useState, useEffect, useContext } from "react";
import { Link, useHistory } from "react-router-dom";
import categoryApi from "../../api/categoryApi";
import ToastContext from "../../context/ToastContext";

function CategoryItem(props) {
    const history = useHistory();
    const [category, setCategory] = useState({});
    const idCategory = props.match.params.id;
    const [errors, setErrors] = useState({});
    const { setToastList } = useContext(ToastContext)


    async function handleSubmit(e) {
        e.preventDefault();
        setErrors(validate(category));
        if (Object.keys(errors).length === 0) {
            try {
                const data = JSON.stringify(category);
                const response = await categoryApi.update(idCategory, data);
                if (response.data) {
                    setToastList([{ title: "Success", message: `Success updated new category`, type: 'success' }]);

                }
                else {
                    setToastList([{ title: "Error", message: `Error updated new category`, type: 'error' }])

                }
            } catch (error) {
                console.log(error);
            }
        }
    }

    useEffect(
        async () => {
            try {
                const response = await categoryApi.get(idCategory);
                setCategory(response.data);
            } catch (error) {
                console.log(error);
            }
        }, []);



    const handleName = (e) => {
        setCategory({
            ...category,
            "name": e.target.value
        })
    }

    const handleCode = (e) => {
        setCategory({
            ...category,
            "code": e.target.value
        })
    }
    const handleDescription = (e) => {
        setCategory({
            ...category,
            "description": e.target.value
        })
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


    return (
        <div className="category">
            <h2>Detail category with id: {category.id}</h2>
            <button className="button-back" onClick={history.goBack}>
                <i className="fas fa-backward"></i> Back
            </button>

            <Link to="/categories/create" className="category-new"><h2>New</h2></Link>

            <form className="new-form" id="form-update__category" onSubmit={handleSubmit}>
                <div className="form-group" style={{ display: 'none' }}>
                    <label htmlFor="id" className="form-label">Id</label>
                    <input id="id" className="form-control" name="id" type="text" defaultValue={category.id || ''} />
                    <span className="form-message"></span>
                </div>
                <div className={errors.name ? "form-group invalid" : "form-group"}>
                    <label htmlFor="name" className="form-label">Name</label>
                    <input id="name" className="form-control" name="name" type="text" onChange={handleName} defaultValue={category.name || ''} />
                    <span className="form-message">{errors.name}</span>
                </div>

                <div className={errors.code ? "form-group invalid" : "form-group"}>
                    <label htmlFor="code" className="form-label">Code</label>
                    <input id="code" className="form-control" name="code" type="text" onChange={handleCode} defaultValue={category.code || ''} />
                    <span className="form-message">{errors.code}</span>
                </div>
                <div className={errors.description ? "form-group invalid" : "form-group"}>
                    <label htmlFor="description" className="form-label">Description</label>
                    <input id="description" className="form-control" name="description" type="text" onChange={handleDescription} defaultValue={category.description || ''} />
                    <span className="form-message">{errors.description}</span>
                </div>
                <div className="form-group">
                    <label htmlFor="createdAt" className="form-label">Created at</label>
                    <input disabled id="createdAt" className="form-control" name="createdAt" defaultValue={category.createdAt || ''} />
                    <span className="form-message"></span>
                </div>
                <div className="form-group">
                    <label htmlFor="updatedAt" className="form-label">Updated at</label>
                    <input disabled id="updatedAt" className="form-control" name="updatedAt" defaultValue={category.updatedAt || ''} />
                    <span className="form-message"></span>
                </div>
                <button className="form-submit">Update</button>
            </form>
        </div>
    );
}

export default CategoryItem;