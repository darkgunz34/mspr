package fr.mspr.model.exception;

public final class CustomGetMessage {
    private CustomGetMessage(){

    }

    public static String recuperationDerniereLigneException(final String entete, final StackTraceElement[] traceElements, final int nombreDeLigne){
        final StringBuilder stackTrace= new StringBuilder();
        stackTrace.append(entete);
        stackTrace.append("\n");
        for (int i = 0; i < nombreDeLigne; i++) {
            stackTrace.append(traceElements[i]);
            stackTrace.append("\n");
        }
        return stackTrace.toString();
    }
}
