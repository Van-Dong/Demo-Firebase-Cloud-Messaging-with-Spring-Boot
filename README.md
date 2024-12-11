# Cách cài đặt và sử dụng FCM 
## B1: Cài đặt cho Backend
* Đầu tiên, vào https://console.firebase.google.com/u/0 , sau đó tạo project 
* Tiếp theo, vào `Project Settings` => `Service accounts` => `Java` => `Generate new private key`
* Copy file vừa tải (private key đấy) vào thư mục `resources` trong project
* Thêm thuộc tính trỏ đến tên file vừa copy trong application.yaml 
* Thêm phụ thuộc `<groupId>com.google.firebase</groupId>
            <artifactId>firebase-admin</artifactId>`
* Sau đó tiến hành cấu hình FireBase trong Spring Boot (xem code tham khảo)
  * Sau khi cấu hình xong, ta sẽ có một Bean gọi là FireBaseMessageing dùng để tương tác với API của FireBase Cloud

## B2: Cài đặt cho Front-end (cài SDK FireBase cho Front-End)
* Đầu tiên, vào https://console.firebase.google.com/u/0 , sau đó tạo project
* Tiếp theo, vào `Project Settings` => `General` => `Add app` => `Web` => Viết tên app sẽ đăng ký => `Register app`
* Copy cấu hình SDK vào Code Front-End của ta để nó có thể đăng ký chính mình với FCM
* Sau khi lấy token từ FCM, ta gọi API của backend để gửi token vừa đăng ký cho backend
* Viết code xử lý sự kiện khi nhận message từ FCM

## Chú ý:
* Do đây chỉ là demo nên backend tôi sẽ tạo một endpoint để post-man gửi FCM Token và tin nhắn. 
* Backend sẽ nhận được request từ postman, sau đó gửi notification đến token được chỉ định
* Front-end sẽ nhận được notification 