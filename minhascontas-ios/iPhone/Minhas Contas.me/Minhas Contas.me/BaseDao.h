//
//  BaseDao.h
//  Minhas Contas.me
//
//  Created by Orlando Burli on 17/11/14.
//  Copyright (c) 2014 Orlando Burli Projetos de TI Personalizados. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface BaseDao : NSObject

-(NSInteger) getMaxId : (NSString *) tableName withMaxField : (NSString *) maxField;

@end
