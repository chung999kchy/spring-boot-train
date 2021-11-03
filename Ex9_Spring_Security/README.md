# Ex8_Spring_Security

## JWT là gì?

- JWT là Json Web Token, định nghĩa một cách nhỏ gọn và khép kín để truyền một cách an toàn thông tin giữa các bên dưới dạng đối tượng JSON.
  Thông tin này có thể được xác minh và đáng tin cậy vì nó có chứa chữ ký số.
  JWTs có thể được ký bằng một thuật toán bí mật (với thuật toán HMAC) hoặc một public / private key sử dụng mã hoá RSA.
  
- Lợi ích:
  + Phân quyền (Authorization) : Khi người dùng đã đăng nhập, mỗi request tiếp theo được gởi từ Client sẽ bao gồm JWT, cho phép người dùng access vào routes, services, and resources được phép với token đó.
  + Trao đổi thông tin giữa 2 bên Client và Server một cách an toàn.
  
- Cấu trúc: gồm 3 phần phân tách nhau bởi dấu `.`
  
  Header Payload Signature
  
```
<base64-encoded header>.<base64-encoded payload>.<base64-encoded signature>
```

=> Nói một cách khác, JWT là sự kết hợp (bởi dấu `.`) một Object Header dưới định dạng JSON được encode base64, một payload object dưới định dạng JSON được encode base64 và một Signature cho URI cũng được mã hóa base64 nốt.


### Header
Trong header gồm có 2 phần, đó là: 
  + loại mã token, đó là Typ (đại điện cho Type), giá trị là `JWT`
  + thuật toán được sử dụng,đó là Alg (đại diện cho Algorithm), giá trị chẳng hạn HMAC SHA256 hoặc RSA.

Ví dụ:
```Json
{
  "alg": "HS256",
  "typ": "JWT"
}
```

### Payload

- chúng chứa những nội dung với thông tin mà người sử dụng muốn có ở bên trong chuỗi. Những thông tin này góp phần mô tả thực thể đơn giản và nhanh chóng, ngoài ra bổ sung thêm cho phần header.
- Payload được chia ra làm 3 loại thông tin chính, đó là: Thông tin đã đăng ký, thông tin riêng tư và thông tin công khai.
  + Thông tin đã đăng ký: Đây là những thông tin được quy định bởi tổ chức IANA JSON WEB TOKEN, thế nhưng những thông tin này lại không mang tính chất bắt buộc. Có thể tùy vào từng ứng dụng khác nhau để đặt ra những điều kiện bắt buộc, từ đó đưa ra những thông tin cần thiết nhất cho ứng dụng của mình. Một số điều kiện nên có: Tổ chức phát hành token, chủ đề của token, đối tượng sử dụng token là ai, thời điểm hết hạn sử dụng token, token chưa hợp lệ ở thời điểm phát hành, thời điểm phát hành token, id của JWT.
  + Thông tin riêng tư: Nó được coi là phần thông tin thêm, được sử dụng để truyền tải qua lại giữa các máy khách.
  + Thông tin công khai: Mang đến những định nghĩa tùy theo các ý muốn của người sử dụng JWT, từ đó tránh được trùng lặp xảy ra.
  

### Signature
- đây là một phần chữ ký bí mật, được tạo bởi mã hóa phần header cùng với cập nhật phần Payload kèm theo cùng một chuỗi secret. 
- Nguyên tắc:
```
HMACSHA256(
  base64UrlEncode(header) + "." +
  base64UrlEncode(payload),
  secret)
```

- Do bản thân Signature đã bao gồm cả header và payload nên Signature có thể dùng để kiểm tra tính toàn vẹn của dữ liệu khi truyền tải.
- trong trường hợp token được ký bằng private key, nó cũng có thể xác minh người gửi JWT.
