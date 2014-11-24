//
//  BaseServices.m
//  Minhas Contas.me
//
//  Created by Orlando Burli on 14/11/14.
//  Copyright (c) 2014 Orlando Burli Projetos de TI Personalizados. All rights reserved.
//

#import "BaseServices.h"

@implementation BaseServices

// Método genérico para comunicação com webservice REST.
// post : Parametros no formato HTML parametro=valor&parametro2=valor2
// withService (serviceUrl) : Url do Serviço. Exemplo: /acesso/criar

- (NSString *)sendPostData:(NSString *)post withService: (NSString *) serviceUrl {
    
    NSData *postData = [post dataUsingEncoding:NSASCIIStringEncoding allowLossyConversion:YES];
    
    NSString *postLength = [NSString stringWithFormat:@"%ld", (long)[postData length]];
    
    NSMutableURLRequest *request = [[NSMutableURLRequest alloc] init];
    
    [request setURL:[NSURL URLWithString:[NSString stringWithFormat:@"%@%@", BASE_URL , serviceUrl]]];
    
    [request setHTTPMethod:@"POST"];
    
    [request setValue:postLength forHTTPHeaderField:@"Content-Length"];
    
    [request setValue:@"application/x-www-form-urlencoded" forHTTPHeaderField:@"Current-Type"];
    
    [request setHTTPBody:postData];
    
    NSURLResponse * response = nil;
    
    NSError * error = nil;
    NSData * data = [NSURLConnection sendSynchronousRequest:request
                                          returningResponse:&response
                                                      error:&error];
    
    if (error != nil) {
        // Parse data here
        NSLog(@"Erro: %@", error.description);
        
        return error.description;
    } else {
        NSString *retorno = [[NSString alloc] initWithData:data encoding:NSUTF8StringEncoding];
    
        NSLog(@"Retorno: %@", retorno);
        
        return retorno;
    }
}



@end
