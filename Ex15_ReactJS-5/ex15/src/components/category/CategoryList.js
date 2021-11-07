/* eslint-disable react-hooks/exhaustive-deps */
import React, { useEffect, useState } from "react";
import { Link, useHistory } from "react-router-dom";
import Pagination from "../pagination/Pagination";
import DeleteCategory from "./DeleteCategory";
import categoryApi from "../../api/categoryApi";
import "./category.scss";

function CategoryList() {
    const history = useHistory();
    const [categories, setCategories] = useState([]);

    const [pagination, setPagination] = useState({
        total: 1,
        limit: 10,
        page: 1
    });

    function handlePageChange(newPage) {
        setPagination({
            ...pagination,
            page: newPage
        });
    }

    useEffect(
        async () => {
            try {
                const params = {
                    page: pagination.page,
                    limit: pagination.limit
                };
                const response = await categoryApi.getAll(params);
                setCategories(response.data);
                setPagination(response.metadata);
            } catch (error) {
                console.log(error);
            }
        }, [pagination.page]);

    return (
        <div className="category">
            <h2>List category</h2>
            <button className="button-back" onClick={history.goBack}>
                <i className="fas fa-backward"></i>  Back
            </button>
            <Link to="/categories/create" className="category-new"><h2>New</h2></Link>
            <ul>
                {categories.map((category) =>
                (
                    <li key={category.id}>
                        <Link to={`/categories/${category.id}`} className="category-list__item">{category.name}</Link>
                        <DeleteCategory id={category.id} />
                    </li>
                ))}
            </ul>
            <Pagination pagination={pagination} handlePageChange={handlePageChange} />
        </div>
    )

}

export default CategoryList;
