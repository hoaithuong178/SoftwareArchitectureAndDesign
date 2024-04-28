# Lab 07 Microkernel
Xây dụng một ứng dụng từ điển áp dụng Microkernel style với yêu cầu sau:
1.	Phần kernel được xây dựng với một phương thức cơ bản là lookup một từ trong từ điển, trả về thông tin của từ cần tra trong ngôn ngữ đích.
2.	Phần UI của core, xây dựng giao diện từ điển (windows app/web app) cho phép tra từ.
3.	Xây dựng các plugins với mỗi plugin là một từ điển để core có thể tham chiếu và tra từ.
4.	Ứng dụng cho phép các từ điển được triển khai trong quá trình ứng dụng được thự thi.
<br>Gợi ý:
-	Các từ điển có thể download trong dự án Open Vietnamese Dictionary Project Files.
-	Sử dụng cấu trúc dữ liệu Map<K,V> để lưu trữ từ điển cho việc tra từ tối ưu hoặc sử dụng một cơ chế nào đó.