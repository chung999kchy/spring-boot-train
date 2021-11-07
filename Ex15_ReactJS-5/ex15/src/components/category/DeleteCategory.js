import React from 'react';
import categoryApi from '../../api/categoryApi';

function DeleteCategory({ id }) {

    const handleClick = async (e) => {
        e.preventDefault();
        try {
            const response = await categoryApi.delete(id);
            alert(response);
        } catch (error) {
            console.log(error);
        }
    }
    return (
        <button onClick={handleClick} className="category-delete">
            <i className="fas fa-trash-alt"></i>
        </button>
    );
}

export default DeleteCategory;