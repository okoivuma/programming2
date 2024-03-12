package dev.m3s.programming2.homework2;

public class PersonID {
    private String birthDate = ConstantValues.NO_BIRTHDATE;

    public String getBirthDate(){
        return birthDate;
    }

    public String setPersonId(final String personId){
        if (personId != null && !personId.isEmpty()) {
            String year;
            if (checkPersonIDNumber(personId)) {
                char centChar = personId.charAt(6);

                year = switch (centChar) {
                    case '+' -> "18";
                    case '-' -> "19";
                    case 'A' -> "20";
                    default -> "virheellinen merkki";
                };

                String bDate = personId.substring(0, 2) + "." + personId.substring(2, 4) + "." + year + personId.substring(4, 6);

                if (checkBirthdate(bDate)) {

                    if (!checkValidCharacter(personId)) {
                        return ConstantValues.INCORRECT_CHECKMARK;
                    }

                } else {
                    return ConstantValues.INVALID_BIRTHDAY;
                }
                birthDate = bDate;
                return "Ok";
            } else {
                return ConstantValues.INVALID_BIRTHDAY;
            }
        } else
            return ConstantValues.INVALID_BIRTHDAY;
    }

    private boolean checkPersonIDNumber(final String personID){
        char centChar = personID.charAt(6);
        return personID.length() == 11 && (centChar == '+' || centChar == 'A' || centChar == '-');
    }

    private boolean checkLeapYear(int year){
        return (year % 4 == 0) && (year % 100 != 0) || (year % 400 == 0);
    }

    private boolean checkValidCharacter(final String personID){
        char c = personID.charAt(personID.length() - 1);
        char [] controlChar = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D',
                'E', 'F', 'H', 'J', 'K', 'L','M', 'N', 'P', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y'};

        String numbersOfID = personID.substring(0,6) + personID.substring(7,10);
        int valueOfID = Integer.parseInt(numbersOfID);
        valueOfID = (valueOfID % 31);

        return controlChar[valueOfID] == c;
    }

    private boolean checkBirthdate(final String date){

        if (date.length() != 10){
            return false;
        }

        int day = Integer.parseInt(date.substring(0,2));
        int month = Integer.parseInt(date.substring(3,5));
        int year = Integer.parseInt(date.substring(6,10));

        if (day >= 1 && day <= 31 && month >=1 && month <= 12 && year > 0){

            if ((month == 4 || month == 6 || month == 9 || month == 11) && day > 30){
                return false;
            } else if (month == 2){
                if (checkLeapYear(year) && day > 29 || !checkLeapYear(year) && day > 28){
                    return false;
                }
            }
            return true;
        }
        return false;
    }
}
