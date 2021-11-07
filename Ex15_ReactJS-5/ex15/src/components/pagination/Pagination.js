import React from 'react';
import "./pagination.scss";

Pagination.defaultProps = {
    handlePageChange: null,
}

function Pagination(props) {
    const { pagination, handlePageChange } = props;
    const { total, limit, page } = pagination;
    const totalPages = Math.ceil(total / limit);

    function handleClick(newPage) {
        if (handlePageChange) {
            handlePageChange(newPage);
        }
    }

    return (
        <div className="pagination">
            <button disabled={page <= 1} onClick={() => handleClick(page - 1)}>
                Prev
            </button>
            {(
                page - 1 >= 1 &&
                <button disabled={page - 1 < 1} onClick={() => handleClick(page - 1)}>
                    {page - 1}
                </button>
            )}
            <button disabled={true}>
                {page}
            </button>
            {(
                page + 1 <= totalPages &&
                <button disabled={page + 1 > totalPages} onClick={() => handleClick(page + 1)}>
                    {page + 1}
                </button>
            )}
            {(
                page + 2 <= totalPages &&
                <button disabled={page + 2 > totalPages} onClick={() => handleClick(page + 2)}>
                    {page + 2}
                </button>
            )}
            <button disabled={page >= totalPages} onClick={() => handleClick(page + 1)}>
                Next
            </button>

        </div>
    );
}

export default Pagination;