//
//  BaseServices.h
//  Minhas Contas.me
//
//  Created by Orlando Burli on 14/11/14.
//  Copyright (c) 2014 Orlando Burli Projetos de TI Personalizados. All rights reserved.
//

#import <Foundation/Foundation.h>

// Esse endereço é da wifi do TCE (wlan-convidados)
#define BASE_URL @"http://10.31.1.159:8080/minhascontas-services/services"

// Esse endereço é da wifi de casa...
//#define BASE_URL @"http://192.168.0.101:8080/minhascontas-services/services"

@interface BaseServices : NSObject

- (NSString *)sendPostData:(NSString *)post withService: (NSString *) serviceUrl;

@end
