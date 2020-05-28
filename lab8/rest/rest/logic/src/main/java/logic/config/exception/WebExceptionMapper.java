package logic.config.exception;

import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class WebExceptionMapper implements ExceptionMapper<WebException> {

    @Produces({"application/json"})
    public Response toResponse(WebException e) {
        ExceptionDto exceptionDto = new ExceptionDto(
                e.getErrorCode(),e.getHttpStatus(),e.getMessage()
        );
        return Response.status(e.getHttpStatus()).entity(exceptionDto).build();
    }
}
