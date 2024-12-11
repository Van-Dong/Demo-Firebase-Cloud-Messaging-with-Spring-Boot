// This file must be in root folder
// Đặt ở root project thì trình duyệt sẽ tự động đăng ký Service worker cho file có đuôi (sw.js)
// Xem ở devtool -> application -> Service Worker

// File này cần để đăng ký với SW khi ứng dụng không hoạt động thì thông báo vẫn được đẩy 

importScripts('https://www.gstatic.com/firebasejs/9.14.0/firebase-app-compat.js');
importScripts('https://www.gstatic.com/firebasejs/9.14.0/firebase-messaging-compat.js');

const firebaseConfig = {
    apiKey: "AIzaSyAbu6GOlGTrQSOdDr-e7wPPY6BaPUV9bSU",
    authDomain: "fir-pns-spring-boot.firebaseapp.com",
    projectId: "fir-pns-spring-boot",
    storageBucket: "fir-pns-spring-boot.firebasestorage.app",
    messagingSenderId: "239157528246",
    appId: "1:239157528246:web:76ca94d5bcb01a8ef58817"
};

// receiving messages in background  (cái này chỉ hiển thị title và body mặc định thôi)
const app = firebase.initializeApp(firebaseConfig)
const messaging = firebase.messaging()

// custom message pop up
// Mặc dù custom thành công nhưng nó lại hiển thị 2 lần, một lần là thông báo của ta, một lần là mặc định
// messaging.onBackgroundMessage(function (payload) {
//     const extraData = payload.data['key1']; // Trong Map<String, String> đấy 

//     const notificationOptions = {
//         body: payload.notification.body,
//         // icon: payload.notification.icon, // ??
//         image: payload.notification.imageUrl
//     }

//     // Hiển thị thông bao tùy chỉnh 
//     self.registration.showNotification(payload.notification.title, notificationOptions);
//     self.addEventListener('notificationclick', function (event) {
//         const clickedNotification = event.notification
//         clickedNotification.close();

//         // Mở cửa sổ mới khi người dùng nhấn vào thông báo
//         event.waitUntil(
//             clients.openWindow(payload.data.click_action)
//         )
//     })
// })


/*
// Dạng thông báo (message recieved) sẽ như bên dươi
{
    "collapseKey": undefined,
    "data":  {"key1": 'value 1', "key2": 'value 2'},
    "from":  "239157528246",
    "messageId": "9fcd1bfc-879c-4f6c-9722-2db068b39624",
    "notification": 
      {
        "title": 'WW3',
        "body": 'loài người bị diệt mất 99%', 
        "imageUrl": 'https://www.chinausfocus.com/d/file/202407/ac87934040a0a386634a50a6cc4ebdf2.jpg'
     }
}
     */
