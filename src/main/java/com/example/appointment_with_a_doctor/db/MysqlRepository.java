package com.example.appointment_with_a_doctor.db;

import com.example.appointment_with_a_doctor.Doctor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.sql.*;

@Service
public class MysqlRepository implements DoctorRepository, UsersRepository {

//    private final static String url = "jdbc:mysql://localhost:3306/hospital_base";
//    private final static String user = "root";
//    private final static String password = "1234";

    private final static String url = "jdbc:postgresql://localhost:8999/studs";
    private final static String user = "s309681";
    private final static String password = "yvr557";

//    @Override
//    public Iterable<Doctor> findAllDoctors() {
//        // можно посмотреть всех врачей (скорее всего эта функция не пригодится :)
//        ArrayList<Doctor> doctors = new ArrayList<>();
//        try (Connection connection = DriverManager.getConnection(url, user, password);
//             Statement statement = connection.createStatement();
//             ResultSet resultSet = statement.executeQuery("select * from `hospital_base`.`doctors_accounts`;")) {
//            while (resultSet.next()) {
//                Doctor doctor = new Doctor();
//                doctor.setId(resultSet.getLong("id"));
//                doctor.setEmail(resultSet.getString("email"));
//                doctor.setPassword(resultSet.getString("password"));
//                doctor.setFirstName(resultSet.getString("first_name"));
//                doctor.setSecondName(resultSet.getString("second_name"));
//                doctor.setLastName(resultSet.getString("last_name"));
//                doctor.setSpecialization(resultSet.getString("specialization"));
//                doctor.setWorkingDays(resultSet.getString("working_days"));
//                doctor.setWorkingHours(resultSet.getString("working_hours"));
//                doctor.setPhoto(resultSet.getString("photo"));
//                doctors.add(doctor);
//            }
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//        return doctors;
//    }

    @PostConstruct
    public void createDoctorsTable() {
        try (Connection connection = DriverManager.getConnection(url, user, password);
             Statement statement = connection.createStatement()) {
            String sql = "CREATE TABLE DOCTOR_ACCOUNTS" +
                    " (last_name TEXT," +
                    "first_name TEXT default ''," +
                    "second_name TEXT default ''," +
                    "specialization TEXT default ''," +
                    "working_days TEXT default 'false|false|false|false|false|false|false'," +
                    "working_hours TEXT default '0|0'," +
                    "photo TEXT default ''," +
                    "email TEXT default ''," +
                    "password TEXT default ''" +
                    ")";
            statement.executeUpdate(sql);
//            statement.close();
//            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Doctor saveDoctor(Doctor doctor) {
        try (Connection connection = DriverManager.getConnection(url, user, password);
             Statement statement = connection.createStatement()) {
            String str = "UPDATE DOCTOR_ACCOUNTS SET last_name" +
                    " = '" + doctor.getLastName()+"', first_name = '"
                    +doctor.getFirstName()+"', second_name = '"
                    +doctor.getSecondName()+"', specialization = '"
                    +doctor.getSpecialization()+"', working_days = '"
                    +doctor.getWorkingDays()+"', working_hours = '"
                    +doctor.getWorkingHours()+"', photo = '"
                    +doctor.getPhoto()+"' WHERE (email = '"
                    +doctor.getEmail()+"');";
            System.out.println(str);
            statement.executeUpdate(str);
//            statement.close();
//            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return doctor;
    }

//    @Override
//    public Doctor findDoctorById(Long id) {
//        //по id находим нужного доктора в базе и возвращаем объект доктор
//        Doctor doctor = new Doctor();
//        try (Connection connection = DriverManager.getConnection(url, user, password);
//             Statement statement = connection.createStatement()) {
//            ResultSet resultSet = statement.executeQuery("select * from" +
//                    " `hospital_base`.`doctors_accounts` where id='"
//                    + id + "';");
//            if (resultSet.next()) {
//
//                doctor.setId(id);
//                doctor.setEmail(resultSet.getString("email"));
//                doctor.setPassword(resultSet.getString("password"));
//                doctor.setFirstName(resultSet.getString("first_name"));
//                doctor.setSecondName(resultSet.getString("second_name"));
//                doctor.setLastName(resultSet.getString("last_name"));
//                doctor.setSpecialization(resultSet.getString("specialization"));
//                doctor.setWorkingDays(resultSet.getString("working_days"));
//                doctor.setWorkingHours(resultSet.getString("working_hours"));
//                doctor.setPhoto(resultSet.getString("photo"));
//            }
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//
//        return doctor;
//    }

    @Override
    public Doctor findDoctorByUsername(String email) {
        //по id находим нужного доктора в базе и возвращаем объект доктор
        Doctor doctor = null;
        try (Connection connection = DriverManager.getConnection(url, user, password);
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("select * from" +
                    " DOCTOR_ACCOUNTS where email='"
                    + email + "';");
            if (resultSet.next()) {
                doctor = new Doctor();
                doctor.setEmail(resultSet.getString("email"));
                doctor.setPassword(resultSet.getString("password"));
                doctor.setFirstName(resultSet.getString("first_name"));
                doctor.setSecondName(resultSet.getString("second_name"));
                doctor.setLastName(resultSet.getString("last_name"));
                doctor.setSpecialization(resultSet.getString("specialization"));
                doctor.setWorkingDays(resultSet.getString("working_days"));
                doctor.setWorkingHours(resultSet.getString("working_hours"));
                doctor.setPhoto(resultSet.getString("photo"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return doctor;
    }

//    @Override
//    public Iterable<User> findAllUsers() {
//        return null;
//    }
//
//    @Override
//    public User saveUser(User doctor) {
//        return null;
//    }
//
//    @Override
//    public User findUserById(Long id) {
//        return null;
//    }
//
//    @Override
//    public User findUserByUsername(String username) {
//        return null;
//    }







//
//    // все пароли и емэйлы заранее известны и внесены в базу по условиям задачи
//    //пытаемся войти в аккаунт, проверяем верны ли пароль и емэйл
//    public Doctor checkAuthorization(String email, String password) throws IncorrentDataForAuthorization {
//        //проверка корректности почты
//        EmailValidator validator = EmailValidator.getInstance();
//        if(validator.isValid(email)){
//            //проверка есть ли такой аккаунт
//            try (Connection connection = DriverManager.getConnection(url, user, password);
//                 Statement statement = connection.createStatement()) {
//                ResultSet resultSet = statement.executeQuery("select * from" +
//                        " `hospital_base`.`doctors_accounts` where email='"
//                        + email+ "' and password='"+password+"';");
//                if (resultSet.next()) {
//                    Doctor doctor = new Doctor();
//                    doctor.setId(resultSet.getLong("id"));
//                    doctor.setEmail(resultSet.getString("email"));
//                    doctor.setPassword(resultSet.getString("password"));
//                    doctor.setFirstName(resultSet.getString("first_name"));
//                    doctor.setSecondName(resultSet.getString("second_name"));
//                    doctor.setLastName(resultSet.getString("last_name"));
//                    doctor.setSpecialization(resultSet.getString("specialization"));
//                    doctor.setWorkingDays(resultSet.getString("working_days"));
//                    doctor.setWorkingHours(resultSet.getString("working_hours"));
//                    doctor.setPhoto(resultSet.getString("photo"));
//                    return doctor;
//                } else {
//                    throw new IncorrentDataForAuthorization("Incorrent data.");
//                }
//            } catch (SQLException throwables) {
//                throwables.printStackTrace();
//            }
//        } else {
//            throw new IncorrentDataForAuthorization("Invalid email.");
//        }
//        return null;
//    }
//
//    // проверка пользователь первый раз заходит или нет (есть ли в базе данные из формы или только его пароль и почта)
//    public boolean checkFirstVisit(Doctor doctor){
//        //так как фамилия  - это обязательное для заполнения поле в форме
//        return doctor.getLastName() == null;
//    }
//
//    @Override
//    public Iterable<Doctor> findAll() {
//        // можно посмотреть всех врачей (скорее всего эта функция не пригодится :)
//        ArrayList<Doctor> doctors = new ArrayList<>();
//        try (Connection connection = DriverManager.getConnection(url, user, password);
//             Statement statement = connection.createStatement();
//             ResultSet resultSet = statement.executeQuery("select * from `hospital_base`.`doctors_accounts`;")) {
//            while (resultSet.next()) {
//                Doctor doctor = new Doctor();
//                doctor.setId(resultSet.getLong("id"));
//                doctor.setEmail(resultSet.getString("email"));
//                doctor.setPassword(resultSet.getString("password"));
//                doctor.setFirstName(resultSet.getString("first_name"));
//                doctor.setSecondName(resultSet.getString("second_name"));
//                doctor.setLastName(resultSet.getString("last_name"));
//                doctor.setSpecialization(resultSet.getString("specialization"));
//                doctor.setWorkingDays(resultSet.getString("working_days"));
//                doctor.setWorkingHours(resultSet.getString("working_hours"));
//                doctor.setPhoto(resultSet.getString("photo"));
//                doctors.add(doctor);
//            }
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//        return doctors;
//    }
//
//    @Override
//    public Doctor save(Doctor doctor) {
//        // для сохранения данных с формы в базу
//        try (Connection connection = DriverManager.getConnection(url, user, password);
//             Statement statement = connection.createStatement()) {
//            String str1 = "UPDATE `hospital_base`.`doctors_accounts` SET `last_name`" +
//                    " = '"+doctor.getLastName()+"', `first_name` = '"
//                    +doctor.getFirstName()+"', `second_name` = '"
//                    +doctor.getSecondName()+"', `specialization` = '"
//                    +doctor.getSpecialization()+"', `working_days` = '"
//                    +doctor.getWorkingDays()+"', `working_hours` = '"
//                    +doctor.getWorkingHours()+"', `photo` = '"
//                    +doctor.getPhoto()+"' WHERE (`id` = '"
//                    +doctor.getId()+"');";
//            statement.executeUpdate(str1);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return doctor;
//    }
//
//
//    @Override
//    public Doctor findDoctor(Long id) {
//        //по id находим нужного доктора в базе и возвращаем объект доктор
//        Doctor doctor = new Doctor();
//        try (Connection connection = DriverManager.getConnection(url, user, password);
//             Statement statement = connection.createStatement()) {
//            ResultSet resultSet = statement.executeQuery("select * from" +
//                    " `hospital_base`.`doctors_accounts` where id='"
//                    + id + "';");
//            if (resultSet.next()) {
//
//                doctor.setId(id);
//                doctor.setEmail(resultSet.getString("email"));
//                doctor.setPassword(resultSet.getString("password"));
//                doctor.setFirstName(resultSet.getString("first_name"));
//                doctor.setSecondName(resultSet.getString("second_name"));
//                doctor.setLastName(resultSet.getString("last_name"));
//                doctor.setSpecialization(resultSet.getString("specialization"));
//                doctor.setWorkingDays(resultSet.getString("working_days"));
//                doctor.setWorkingHours(resultSet.getString("working_hours"));
//                doctor.setPhoto(resultSet.getString("photo"));
//            }
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//
//        return doctor;
//    }
}
