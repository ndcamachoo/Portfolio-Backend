let anim = document.querySelector(".anim_container");
let homeButton = document.querySelector(".errorPage button");

lottie.loadAnimation({
    container: anim, 
    renderer: 'svg',
    loop: true,
    autoplay: true,
    path: '../assets/PHError.json'
});

lottie.setSpeed(0.5)

homeButton.addEventListener("click",()=> {
    window.location.pathname= '/';
})
