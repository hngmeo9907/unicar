(function () {
    'use strict';
    var app = angular.module('LimoVip', []);
    app.controller('AppointmentController',
       function ($scope, $filter, $http, $window) {
           $scope.reg_mail = /^[A-Za-z0-9]+([_\.\-]?[A-Za-z0-9])*@[A-Za-z0-9]+([\.\-]?[A-Za-z0-9]+)*(\.[A-Za-z]+)+$/;
           $scope.appInfo = {
               CarId: 0,
               ServiceId: 0,
               CustomerName: '',
               CustomerEmail: '',
               CustomerPhone: '',
               CustomerAddress: '',
               Price: '',
               AppTime: ''
           };
           $scope.listCar = [];
           $scope.listService = [];

           //Khởi tạo giá trị
           Initialization();

           function Initialization() {
               $http.get('/umbraco/api/Appointment/GetListMode')
                   .success(function (list) {
                       list.unshift({Id: 0, Name: 'Chọn mode xe'})
                       $scope.listCar = list;
                   })
                   .error(function (error) { console.log(error); });

               $http.get('/umbraco/api/Appointment/GetListService')
                   .success(function (list) {
                       list.unshift({ Id: 0, Name: 'Chọn dịch vụ cần' })
                       $scope.listService = list;
                   })
                   .error(function (error) { console.log(error); });
           }

           $scope.insertAppointment = function (data) {
               if (validateData(data)) {
                   data.AppTime = $("#apptime").datepicker("getDate");

                   $(".load-page").show();
                   $http({
                       method: "POST",
                       url: "/umbraco/api/Appointment/Insert",
                       data: data,
                       cache: false
                   })
                   .success(function (result) {
                       if (result.type == "success") {
                           if (language === 'vi-VN') {
                               toastr.success("Bạn đã tạo lịch hẹn thành công. Chúng tôi sẽ liên lạc với bạn để xác nhận thông tin!");
                           }
                           else {
                               toastr.success("You have successfully created an appointment. We will contact you to confirm the information!");
                           }  
                       }
                       else {
                           if (language === 'vi-VN') {
                               toastr.error("Có lỗi hệ thống đã xảy ra. Vui lòng thử lại!");
                           }
                           else {
                               toastr.error("System error has occurred. Please try again!");
                           }                     
                       }
                   })
                   .error(function (error) { console.log(error); });
                   $(".load-page").hide();
               }
           }

           function validateData(data) {
               toastr.options = {
                   "positionClass": "toast-top-center",
                   "timeOut": "3500",
                   "preventDuplicates": true
               }

               if (data.CustomerName === '') {
                   if (language === 'vi-VN') {
                       toastr.warning("Vui lòng nhập tên của bạn!");
                   }
                   else {
                       toastr.warning("Please enter your name!");
                   }

                   $('#appname').focus();
                   return false;
               }
               else if (data.CustomerEmail === '') {
                   if (language === 'vi-VN') {
                       toastr.warning("Vui lòng nhập email của bạn!");
                   }
                   else {
                       toastr.warning("Please enter your email!");
                   }

                   $('#appemail').focus();
                   return false;
               }
               else if ($scope.reg_mail.test(data.CustomerEmail) === false) {
                   if (language === 'vi-VN') {
                       toastr.error("Email đã nhập không hợp lệ!");
                   }
                   else {
                       toastr.error("The email entered is not valid!");
                   }

                   $('#appemail').focus();
                   return false;
               }
               else if (data.CustomerPhone === '') {
                   if (language === 'vi-VN') {
                       toastr.warning("Vui lòng nhập số điện thoại của bạn!");
                   }
                   else {
                       toastr.warning("Please enter your phone!");
                   }

                   $('#appphone').focus();
                   return false;
               }
               else if (data.AppTime === '') {
                   if (language === 'vi-VN') {
                       toastr.warning("Vui lòng chọn ngày hẹn!");
                   }
                   else {
                       toastr.warning("Please select an appointment date!");
                   }

                   $('#apptime').focus();
                   return false;
               }

               return true;
           }
       });
})();