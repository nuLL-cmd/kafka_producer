package br.com.automatodev.enuns;

import br.com.automatodev.util.UtilLog;
import java.lang.reflect.Field;

public enum EnumContaVtex {

    /** Nome da conta vtex distribuidor */
    DISTRIBUIDOR("automatodevdistribuidor"),

    /** Nome da conta vtex varejo */
    VAREJO("automatodev");

    private final String codigo;

    private EnumContaVtex(String codigo) {
        this.codigo = codigo;
        try {
            Field field = this.getClass().getSuperclass().getDeclaredField("name");
            field.setAccessible(true);
            field.set(this, codigo);
        } catch (Exception e) {
            UtilLog.getLog().error(e.getMessage(), e);
        }
    }

    public String getCodigo() {
        return codigo;
    }
}
