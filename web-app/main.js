// Chú ý: dùng bản compat (namespace API) --> không cần đăng ký Service Worker
// Dùng bản modular API => cần đăng ký Service Worker + dễ bị lỗi nữa (do đây là bản mới, có thể không tương thích) 
//    (t bị lỗi bản này , không thành công triển khai)
//    link: https://www.youtube.com/watch?v=ulYnmSbgeq8

// Chú ý: importScripts chỉ dùng trong web worker thôi, trong file .html thì dùng <script>
// trong file .js thì dùng 
// Dùng cái này thì bị lỗi, không hoạt động được

import 'https://www.gstatic.com/firebasejs/9.14.0/firebase-app-compat.js';
import 'https://www.gstatic.com/firebasejs/9.14.0/firebase-messaging-compat.js';



// Your web app's Firebase configuration
const firebaseConfig = {
    apiKey: "AIzaSyAbu6GOlGTrQSOdDr-e7wPPY6BaPUV9bSU",
    authDomain: "fir-pns-spring-boot.firebaseapp.com",
    projectId: "fir-pns-spring-boot",
    storageBucket: "fir-pns-spring-boot.firebasestorage.app",
    messagingSenderId: "239157528246",
    appId: "1:239157528246:web:76ca94d5bcb01a8ef58817"
};

const app = firebase.initializeApp(firebaseConfig)
const messaging = firebase.messaging()

// lấy token lần đầu (khi token hết hợp lệ thì nó mới hỏi FCM để lấy lại, còn đâu dùng cái cũ)
messaging.getToken({ vapidKey: "BLqGff1g5jw_RxeUVSop3guE7nhsZoeHapQR1NJHF59AddVowvQ1312d2R9lxhlSzTayRKxx_FfRx3OFXfvZhrI" })
    .then(currentToken => {
        console.log("Token register: ", currentToken);
        // Send Token to server
    })
    .catch(err => {
        console.log("Failed Token Register: ", err)
    })


// Xử lý notification khi ứng dụng đang bật (nếu ứng dụng không bật => nó tự động popup cho minh)
// Register in ForeGround
messaging.onMessage(payload => {
    console.log('Message received: ', payload);
}) 

// Request Permission to User (cần người dùng bật thông báo thì mới hiển thị được)
function requestPermission() {
    console.log('Requesting permission...');
    Notification.requestPermission().then((permission) => {
        if (permission === 'granted') {
            console.log('Notification permission granted.');
        }
    })
}

document.querySelector("#request-notification").addEventListener('click', requestPermission)