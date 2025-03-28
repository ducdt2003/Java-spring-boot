/*

// Hàm format ngày theo định dạng dd/MM/yyyy
const formatDate = (dateStr) => {
    const date = new Date(dateStr);
    let day = date.getDate();
    let month = date.getMonth() + 1;
    let year = date.getFullYear();
    day = day < 10 ? '0' + day : day;
    month = month < 10 ? '0' + month : month;
    return `${day}/${month}/${year}`;
};

const renderReviews = (reviews) => {
    if (!Array.isArray(reviews) || reviews.length === 0) {
        console.warn("Không có đánh giá nào để hiển thị.");
        document.querySelector('.review-list').innerHTML = "<p>Chưa có đánh giá nào.</p>";
        return;
    }

    const html = reviews.map(review => {
        const user = review.user || {};
        const avatar = user.avatar || 'https://homepage.momocdn.net/cinema/momo-cdn-api-220615142913-637909001532744942.jpg';
        const displayName = user.displayName || "Người dùng ẩn danh";
        const rating = review.rating ? `⭐️ ${review.rating}/10` : "Chưa có đánh giá";
        const content = review.content || "Không có nội dung";
        const formattedDate = review.createdAt ? formatDate(review.createdAt) : "Không xác định";

        return `
            <div class="review-item">
                <div class="review-author d-flex justify-content-between">
                    <div class="d-flex">
                        <div class="review-author-avatar">
                            <img src="${avatar}" alt="Avatar">
                        </div>
                        <div class="ms-3">
                            <p class="fw-semibold">${displayName}</p>
                            <p>${rating}</p>
                        </div>
                    </div>
                    <div>
                        <p>${formattedDate}</p>
                    </div>
                </div>
                <div class="review-content mt-3">
                    <p>${content}</p>
                </div>
                <div class="review-actions mt-2">
                    <button class="btn btn-sm btn-primary" onclick="editReview(${review.id})">Sửa</button>
                    <button class="btn btn-sm btn-danger" onclick="deleteReview(${review.id})">Xóa</button>
                </div>
            </div>
        `;
    }).join('');

    const reviewList = document.querySelector('.review-list');
    if (reviewList) {
        reviewList.innerHTML = html;
    } else {
        console.error("Không tìm thấy phần tử .review-list trong DOM.");
    }
};


// Hàm render phân trang sử dụng template string
const renderPagination = (totalPages, currentPage) => {
    let paginationHTML = `
        <ul class="pagination justify-content-center">
            ${currentPage <= 1
        ? `<li class="page-item disabled"><button class="page-link" disabled>Previous</button></li>`
        : `<li class="page-item"><button class="page-link" onclick="getReviews(${currentPage - 1})">Previous</button></li>`
    }
    `;

    for (let i = 1; i <= totalPages; i++) {
        paginationHTML += i === currentPage
            ? `<li class="page-item active"><button class="page-link" onclick="getReviews(${i})">${i}</button></li>`
            : `<li class="page-item"><button class="page-link" onclick="getReviews(${i})">${i}</button></li>`;
    }

    paginationHTML += `
            ${currentPage >= totalPages
        ? `<li class="page-item disabled"><button class="page-link" disabled>Next</button></li>`
        : `<li class="page-item"><button class="page-link" onclick="getReviews(${currentPage + 1})">Next</button></li>`
    }
        </ul>
    `;
    document.querySelector('nav.mt-4').innerHTML = paginationHTML;
};


// Hàm lấy reviews (sử dụng async/await, arrow function và try/catch)
const getReviews = async (page) => {
    try {
        const movieId = movie.id;
        const response = await axios.get('/api/reviews', {
            params: {
                movieId: movieId,
                page: page,
                pageSize: 5
            }
        });

        // Giả sử API trả về đối tượng có cấu trúc:
        // { content: [...reviews], totalPages: number, number: currentPage (0-indexed) }
        const reviewPage = response.data;
        renderReviews(reviewPage.content);
        renderPagination(reviewPage.totalPages, reviewPage.number + 1);
    } catch (error) {
        console.log(error);
    }
};

// Hàm xóa review sử dụng async/await và window.confirm
const deleteReview = async (id) => {
    if (!window.confirm("Bạn có chắc chắn muốn xóa review này không?")) {
        return;
    }
    try {
        await axios.delete(`/api/reviews/${id}`);
        // Nếu cần cập nhật lại danh sách review cho trang 1, có thể gọi:
        getReviews(1);
    } catch (error) {
        console.log(error);
    }
};

*/
/* ---- Xử lý tạo review ---- *//*

const reviewForm = document.getElementById("review-form");
const reviewContentEl = document.getElementById("review-content");

// Sử dụng biến global cho rating đã chọn (được cập nhật từ phần chọn rating)
let currentRating = 0;

// Xử lý chọn rating (code hiện tại đã có)
const stars = document.querySelectorAll(".star");
const ratingValue = document.getElementById("rating-value");

stars.forEach((star) => {
    star.addEventListener("mouseover", () => {
        resetStars();
        const rating = parseInt(star.getAttribute("data-rating"));
        highlightStars(rating);
    });

    star.addEventListener("mouseout", () => {
        resetStars();
        highlightStars(currentRating);
    });

    star.addEventListener("click", () => {
        currentRating = parseInt(star.getAttribute("data-rating"));
        ratingValue.textContent = `Bạn đã đánh giá ${currentRating} sao.`;
        highlightStars(currentRating);
    });
});

function resetStars() {
    stars.forEach((star) => {
        star.classList.remove("active");
    });
}

function highlightStars(rating) {
    stars.forEach((star) => {
        const starRating = parseInt(star.getAttribute("data-rating"));
        if (starRating <= rating) {
            star.classList.add("active");
        }
    });
}

// Xử lý submit form tạo review
reviewForm.addEventListener("submit", async (event) => {
    event.preventDefault();

    // Validate rating
    if (currentRating === 0) {
        alert("Vui lòng chọn rating");
        return;
    }

    // Validate content
    const content = reviewContentEl.value.trim();
    if (!content) {
        alert("Vui lòng nhập nội dung");
        return;
    }

    // Tạo payload cho API
    const payload = {
        content: content,
        rating: currentRating,
        movieId: movie.id
    };

    try {
        await axios.post('/api/reviews', payload);
        alert("Tạo review thành công");

        // Đóng modal sử dụng Bootstrap 5
        const modalEl = document.getElementById('modal-review');
        const modalInstance = bootstrap.Modal.getInstance(modalEl) || new bootstrap.Modal(modalEl);
        modalInstance.hide();

        // Reset form và UI rating
        reviewForm.reset();
        currentRating = 0;
        resetStars();
        ratingValue.textContent = "";

        // Nếu muốn, có thể cập nhật lại danh sách review ngay sau khi tạo
        getReviews(1);
    } catch (error) {
        console.log(error);
    }
});

// Khởi chạy load trang đầu tiên
getReviews(1);*/
document.addEventListener("DOMContentLoaded", function () {
    const reviewForm = document.getElementById("review-form");
    const reviewContent = document.getElementById("review-content");
    const reviewList = document.querySelector(".review-list");

    // Xử lý rating
    let selectedRating = 10; // Mặc định là 10
    document.querySelectorAll(".star").forEach((star) => {
        star.addEventListener("click", function () {
            selectedRating = this.getAttribute("data-rating");
            document.getElementById("rating-value").innerText = `⭐️ ${selectedRating}/10`;
        });
    });

    // Xử lý sự kiện submit bình luận
    reviewForm.addEventListener("submit", function (event) {
        event.preventDefault(); // Ngăn chặn reload trang

        const content = reviewContent.value.trim();
        if (content === "") {
            alert("Vui lòng nhập nội dung bình luận.");
            return;
        }

        // Lấy ngày hiện tại
        const currentDate = new Date();
        const formattedDate = `${currentDate.getDate().toString().padStart(2, '0')}/${(currentDate.getMonth() + 1).toString().padStart(2, '0')}/${currentDate.getFullYear()}`;

        // Tạo HTML cho bình luận mới
        const newReview = `
            <div class="review-item">
                <div class="review-author d-flex justify-content-between">
                    <div class="d-flex">
                        <div class="review-author-avatar">
                            <img src="https://yt3.googleusercontent.com/b_j7mPLTnqs5ziD0d8CQO2dIqive_FxeiMDtrQat0mGxWSLUw7Mv2rfvJPMngHVPBcY_dxALPA=s160-c-k-c0x00ffffff-no-rj" alt="">
                        </div>
                        <div class="ms-3">
                            <p class="fw-semibold">Bạn</p>
                            <p>⭐️ ${selectedRating}/10</p>
                        </div>
                    </div>
                    <div>
                        <p>${formattedDate}</p>
                    </div>
                </div>
                <div class="review-content mt-3">
                    <p>${content}</p>
                </div>
            </div>
        `;

        // Thêm bình luận mới vào danh sách
        reviewList.insertAdjacentHTML("afterbegin", newReview);

        // Xóa nội dung textarea sau khi gửi
        reviewContent.value = "";
    });
});
