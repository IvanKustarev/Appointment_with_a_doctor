package com.example.appointment_with_a_doctor;

public enum Specializations {
    Gastroenterolog{
        @Override
        public String toString() {
            return "Гастроэнтеролог";
        }
    },
    Dermotolog{
        @Override
        public String toString() {
            return "Дерматовенеролог";
        }
    },
    Infectionist{
        @Override
        public String toString() {
            return "Инфекционист";
        }
    },
    Kardiolog{
        @Override
        public String toString() {
            return "Кардиолог";
        }
    },
    Nevrolog{
        @Override
        public String toString() {
            return "Невролог";
        }
    },
    Oftalmolog{
        @Override
        public String toString() {
            return "Офтальмолог";
        }
    },
    Terapevt{
        @Override
        public String toString() {
            return "Терапевт";
        }
    },
    Herurg{
        @Override
        public String toString() {
            return "Хирург";
        }
    }
//    Гастроэнтеролог, Дерматовенеролог, Инфекционист,  Кардиолог, Невролог, Офтальмолог, Терапевт, Хирург
}
