var swiper = new Swiper(".novels-swiper", {
	slidesPerView: 4,
	spaceBetween: 30,
	slidesPerGroup: 4,
	loop: true,
	loopFillGroupWithBlank: true,
	navigation: {
		nextEl: ".swiper-button-next",
		prevEl: ".swiper-button-prev",
	},
});

var swiper = new Swiper(".authors-swiper", {
	slidesPerView: 4,
	spaceBetween: 30,
	slidesPerGroup: 4,
	loop: true,
	loopFillGroupWithBlank: true,
	navigation: {
		nextEl: ".swiper-button-next",
		prevEl: ".swiper-button-prev",
	},
});

var swiper = new Swiper(".banner-slider", {
	loop: true,
	loopFillGroupWithBlank: true,
	navigation: {
		nextEl: ".swiper-button-next",
		prevEl: ".swiper-button-prev",
	},
});
