package exception;

public class CustomException {
    public static class DuplicatedMedicalLicenceNumberException extends Exception {
        public DuplicatedMedicalLicenceNumberException(String errorMessage) {
            super(errorMessage);
        }
    }

    public static class DuplicatedMobileNumberException extends Exception {
        public DuplicatedMobileNumberException(String errorMessage) {
            super(errorMessage);
        }
    }

    public static class InvalidDateException extends Exception{
        public InvalidDateException(String errorMessage) {
            super(errorMessage);
        }
    }

    public static class InsufficientSpaceException extends Exception {
        public InsufficientSpaceException(String errorMessage) {
            super(errorMessage);
        }
    }

    public static class InvalidInputException  extends Exception{
        public InvalidInputException(String errorMessage) {
            super(errorMessage);
        }
    }

    public static class InvalidManagementActionException extends Exception{
        public InvalidManagementActionException(String errorMessage) {
            super(errorMessage);
        }
    }
}
