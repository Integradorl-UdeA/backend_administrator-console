package com.consola.lis.util.constans;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ApiDescription {

    //Manage return loans
    public static final String DESCRIPTION_GET_RETURN_ALL_LOANS="Este endpoint permite obtener una lista de los préstamos devueltos.";
    public static final String DESCRIPTION_GET_RETURN_LOAN="Este endpoint permite registrar la devolución de un " +
            "préstamo especificando el ID del préstamo, el usuario prestamista y una observación opcional.";
    public static final String DESCRIPTION_CONTROLLER_RETURN_LOAN="Este grupo de Endpoints maneja las operaciones relacionadas con los préstamos.";

    //Login
    public static final String DESCRIPTION_LOGIN=" Este endpoint permite a los usuarios iniciar sesión proporcionando su nombre de usuario y contraseña. Después de una autenticación exitosa, el sistema responde con un token de autenticación que el usuario puede usar para acceder a recursos protegidos.";
    public static final String DESCRIPTION_CONTROLLER_LOGIN=" Este controlador maneja la operación relacionada con el inicio de sesión de usuario.";

    public static final String DESCRIPTION_REGISTER_USER_LIS="Este endpoint registra un nuevo usuario general en el sistema. Se deben proporcionar los detalles del usuario, como nombre de usuario, identificación, nombre, apellido y contraseña en el cuerpo de la solicitud.";
    public static final String DESCRIPTION_CONTROLLER_REGISTER="Este grupo de Endpoints maneja las operaciones relacionadas con el registro de nuevos usuarios en el sistema.";
    public static final String DESCRIPTION_REGISTER_USER_HELLO_LIS="Este endpoint registra un nuevo usuario auxiliar o administrador en el sistema. Se deben proporcionar los detalles del usuario general agregando una contraseña.";

    //manage categories
    public static final String DESCRIPTION_ALL_CATEGORIES=" Este endpoint permite obtener una lista de todas las categorías de elementos disponibles.";
    public static final String DESCRIPTION_CREATE_CATEGORY="Este endpoint crea una nueva categoría de elementos. Se deben proporcionar detalles como el nombre de la categoría, el identificador, si es cuantificable, y los atributos en el cuerpo de la solicitud.";
    public static final String DESCRIPTION_ONE_CATEGORY="Este endpoint permite obtener los detalles de una categoría específica identificada por su ID de categoría.";
    public static final String DESCRIPTION_DELETE_CATEGORY="Este endpoint permite eliminar una categoría específica identificada por su ID de categoría.";
    public static final String DESCRIPTION_ALL_NAMES_CATEGORIES="Este endpoint permite obtener una lista de nombres de todas las categorías disponibles.";
    public static final String DESCRIPTION_CONTROLLER_CATEGORIES="Este grupo de Endpoints maneja las operaciones relacionadas con la gestión de categorías de elementos.";

    //manage users
    public static final String DESCRIPTION_USER = "Este endpoint permite obtener una lista de todas los usuarios registrados.";
    public static final String DESCRIPTION_ONE_USER =" Este endpoint permite obtener información detallada de un usuario específico identificado por su nombre de usuario. La información incluye el rol del usuario, su identificación, nombre, apellido y nombre de usuario.";
    public static final String DESCRIPTION_USER_LDAP = "Este endpoint obtiene información de usuario desde un servidor LDAP para un usuario específico por su nombre de usuario, incluyendo su rol, identificación, nombre, apellido y nombre de usuario.";
    public static final String DESCRIPTION_DELETE_USER = " Este endpoint permite eliminar un usuario especificado por su nombre de usuario. Se debe ingresar el nombre de usuario como parte de la solicitud para confirmar la eliminación.";
    public static final String DESCRIPTION_CHANGE_ROLE_USER = "Este endpoint actualiza el rol de un usuario específico por su nombre de usuario. Se pueden proporcionar los roles \"ADMIN\", \"AUXPROG\" o \"AUXADMI\" en el cuerpo de la solicitud. La respuesta del servidor incluirá un nuevo token de autenticación.";
    public static final String DESCRIPTION_CONTROLLER_USER="Este grupo de Endpoints maneja las operaciones relacionadas con la gestión de usuarios.";

    //manage loan
    public static final String DESCRIPTION_LOAN = " Este endpoint permite obtener una lista de todos los préstamos registrados en el sistema.";
    public static final String DESCRIPTION_DELETE_LOAN = "Este endpoint permite eliminar un préstamo específico identificado por su ID de préstamo.";
    public static final String DESCRIPTION_CREATE_LOAN = "Este endpoint crea un nuevo préstamo. Se deben proporcionar detalles como el ID del artículo, la cantidad, el usuario prestamista, el usuario prestatario, el tipo de préstamo, la fecha de devolución y una observación opcional.";
    public static final String DESCRIPTION_GET_ONE_LOAN = "Este endpoint permite obtener los detalles de un préstamo específico identificado por su ID de préstamo.";
    public static final String DESCRIPTION_ALL_LOANS_TABLE = "Este endpoint permite obtener registros de la tabla de préstamos mappeados, con paginación y ordenamiento opcional.";
    public static final String DESCRIPTION_HEADERS_LOAN = "Este endpoint permite obtener los encabezados de una tabla de préstamos.";
    public static final String DESCRIPTION_CONTROLLER_LOAN="Este grupo de Endpoints maneja las operaciones relacionadas con la gestión de préstamos.";


    //manage inventory
    public static final String DESCRIPTION_INVENTORY = " Este endpoint permite obtener una lista de elementos de inventario disponibles.";
    public static final String DESCRIPTION_CREATE_ITEM_INVENTORY="Este endpoint agrega un nuevo elemento al inventario. Se necesita proporcionar los detalles del elemento en el cuerpo de la solicitud, incluyendo su ID, categoría, billetera, disponibilidad para préstamo, estado, cantidad y atributos opcionales.";
    public static final String DESCRIPTION_INVENTORY_TABLE = "Este endpoint permite obtener registros de una tabla de elementos de inventario, con paginación y ordenamiento opcional.";
    public static final String DESCRIPTION_DELETE_ITEM="Este endpoint permite eliminar un elemento del inventario especificado por su ID. Se proporciona el ID del elemento como parte de la URL";
    public static final String DESCRIPTION_EDIT_ITEM_STATE="Este endpoint actualiza el estado de disponibilidad de un elemento de inventario específico. El ID del elemento se proporciona en la URL y el nuevo estado (puede ser 'AVAILABLE', 'BROKEN', 'REPAIRING', 'LOST', 'PRESENT', 'OUT_OF_STOCK 'o 'LENDED') se especifica en el cuerpo de la solicitud.\"";
    public static final String DESCRIPTION_ONE_ITEM = "Este endpoint permite obtener detalles específicos de un elemento de inventario identificado por su ID.";
    public static final String DESCRIPTION_EDIT_QUANTITY = "Este endpoint permite actualizar la cantidad de un elemento de inventario específico. Se proporciona el ID del elemento como parte de la URL y se especifica la nueva cantidad en el cuerpo de la solicitud.";
    public static final String DESCRIPTION_HEADERS_ITEM = "Este endpoint permite obtener los encabezados de una tabla de elementos de inventario.";
    public static final String DESCRIPTION_CONTROLLER_INVENTORY="Este grupo de Endpoints maneja las operaciones relacionadas con la gestión de inventario.";
    public static final String DESCRIPTION_EXISTING_USER = "Este Enpoint se encarga de consultar la existencia de un usuario en la db o en el ldap, si existe en alguno de los dos true, de otra forma false; también se hace un mapeo de un usuario existente en el ldap y no en la db a la db";

    //manage select list
    public static final String DESCRIPTION_CONTROLLER_SELECT_LIST = "Este grupo de enpoint permite obtener como una lista los enums que se usan en la api";
    public static final String DESCRIPTION_SELECT_LIST_ENUM_WALLET = "Este enpoint permite obtener la lista de los enums de Wallet Owner pasados a toString y español";
    public static final String DESCRIPTION_SELECT_LIST_ENUM_LOAN_TYPE = "Este enpoint permite obtener la lista de los enums de Loan Type pasados a toString y español";
    public static final String DESCRIPTION_SELECT_LIST_ENUM_ROLE = "Este enpoint permite obtener la lista de los enums de User Role pasados a toString y español";
    public static final String DESCRIPTION_SELECT_LIST_ENUM_LOAN_STATE = "Este enpoint permite obtener la lista de los enums de Loan State pasados a toString y español";
    public static final String DESCRIPTION_SELECT_LIST_ENUM_ITEM_STATE = "Este enpoint permite obtener la lista de los enums de Item State pasados a toString y español";
}

