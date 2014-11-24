//
//  AppGlobal.h
//  Minhas Contas.me
//
//  Created by Orlando Burli on 17/11/14.
//  Copyright (c) 2014 Orlando Burli Projetos de TI Personalizados. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "TokenVo.h"

@interface AppGlobal : NSObject

@property TokenVo *token;

+ (id) instance;

@end
