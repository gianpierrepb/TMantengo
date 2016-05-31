package pe.edu.upc.util;

import pe.edu.upc.model.Cliente;
import pe.edu.upc.service.TMantengoClienteService;
import pe.edu.upc.service.base.BaseService;
import pe.edu.upc.service.impl.TMantengoClienteServiceImpl;

public final class WebUtil {
    
    private WebUtil(){
    }
    
    public static BaseService getService(String clase){
        BaseService baseService = null;
        if (clase.equalsIgnoreCase("Cliente")) {
            baseService = TMantengoClienteServiceImpl.getInstance();
        }
        return baseService;
    }
    
    public static String validarCliente(Cliente cliente) {
        if (cliente.getNombre().isEmpty() || cliente.getApellido().isEmpty() || cliente.getDni().isEmpty()
                || cliente.getEdad().isEmpty() || cliente.getSexo() == null 
                || cliente.getNivelEstudios().equals("SELECCIONAR") || cliente.getTelefono().isEmpty()) {
            return "Debe llenar todos los campos para completar el registro correctamente.";
        }
        if (!validarNombre(cliente.getNombre()) || !validarNombre(cliente.getApellido()) || !esTelefono(cliente.getTelefono())
                || !sizeNombre(cliente.getNombre()) || !sizeNombre(cliente.getApellido()) || !esEdad(cliente.getEdad())
                || !esTelefono(cliente.getTelefono())){
            return "Por favor, ingrese valores validos en los campos.";
        }
        if (!esDni(cliente.getDni(), cliente.getIdCliente())) {
            return "Por favor, ingrese un DNI valido.";
        }
        return null;
    }

    public static String validarCredenciales(String codigo, String password) {
        if (codigo.isEmpty() || password.isEmpty()) {
            return "Por favor, ingrese valores en los campos vacios.";
        }
        if (!esAlfabetico(codigo) || !esAlfanumerico(password)
                || !SizeCredenciales(codigo) || !SizeCredenciales(password)) {
            return "Valores no validos.";
        }
        if (!codigo.equals("root") || !password.equals("root")) {
            return "Password y/o usuario incorrectos.";
        }
        return null;
    }

    public static String[] getArraySexo() {
        String[] arraySexo = new String[4];
        arraySexo[0] = "FEMENINO";
        arraySexo[1] = "Femenino";
        arraySexo[2] = "MASCULINO";
        arraySexo[3] = "Masculino";
        return arraySexo;
    }

    public static String[] getArrayNivelEstudios() {
        String[] arrayEstudios = new String[6];
        arrayEstudios[0] = "SECUNDARIA";
        arrayEstudios[1] = "Secundaria";
        arrayEstudios[2] = "UNIVERSITARIO";
        arrayEstudios[3] = "Universitario";
        arrayEstudios[4] = "TECNICO";
        arrayEstudios[5] = "Tecnico";
        return arrayEstudios;
    }

    public static boolean sizeNombre(String texto) {
        return texto.length() > 3 && texto.length() < 25;
    }

    public static boolean SizeCredenciales(String texto) {
        return texto.length() <= 12 && texto.length() >= 4;
    }

    public static boolean validarNombre(String texto) {
        for (char item : texto.toCharArray()) {
            if (!Character.isLetter(item)) {
                if (!Character.isSpaceChar(item)) {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean esAlfabetico(String texto) {
        return texto.matches("[A-Za-zá-ú]+");
    }

    public static boolean esAlfanumerico(String texto) {
        for (char item : texto.toCharArray()) {
            if (!Character.isLetterOrDigit(item)) {
                return false;
            }
        }
        return true;
    }
    
    public static boolean esTelefono(String telefono) {
        if (telefono.length() != 7 && telefono.length() != 9) {
            return false;
        }
        for (char item : telefono.toCharArray()) {
            if (!Character.isDigit(item)) {
                return false;
            }
        }
        return true;
    }
    
    public static boolean esEdad(String edad) {
        if (edad.length() != 2) {
            return false;
        }
        for (char item : edad.toCharArray()) {
            if (!Character.isDigit(item)) {
                return false;
            }
        }
        return true;
    }
    public static boolean esDni(String dni, Integer clienteId) {
        if (dni.length() != 8) {
            return false;
        }
        for (char item : dni.toCharArray()) {
            if (!Character.isDigit(item)) {
                return false;
            }
        }
        TMantengoClienteService clienteService = (TMantengoClienteService) getService("Cliente");
        try {
            Cliente cliente = clienteService.obtenerClientePorDni(dni);
            if (cliente != null) {
                return cliente.getIdCliente()== clienteId;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }
}
