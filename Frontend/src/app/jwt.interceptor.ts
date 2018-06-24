import { Injectable } from "@angular/core";
import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest, HttpHeaders } from "@angular/common/http";
import { Observable } from "rxjs";

@Injectable()
export class JwtInterceptor implements HttpInterceptor {

    intercept(
        request: HttpRequest<any>,
        next: HttpHandler
    ): Observable<HttpEvent<any>> {
        console.log("Interceeptor");
        let token = JSON.parse(localStorage.getItem('token'));
        console.log("Token: "+token);
        if(token) {
            request = request.clone({
                // headers: new HttpHeaders().append('Authorization', `Bearer${token}`),
                setHeaders: {
                    Authorization: `Bearer${token}`
                }
            })
        }
        console.log("REquest: "+request.body);
        console.log("REquest: "+request.headers);
        console.log("REquest: "+request.method);
        console.log("REquest: "+request.params);
        console.log("REquest: "+request.reportProgress);
        console.log("REquest: "+request.responseType);
        console.log("REquest: "+request.url);
        console.log("REquest: "+request.urlWithParams);
        console.log("REquest: "+request.withCredentials);

        return next.handle(request);
    }

}