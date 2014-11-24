//
//  BaseDao.m
//  Minhas Contas.me
//
//  Created by Orlando Burli on 17/11/14.
//  Copyright (c) 2014 Orlando Burli Projetos de TI Personalizados. All rights reserved.
//

#import "BaseDao.h"
#import "DBManager.h"

@implementation BaseDao

-(NSInteger) getMaxId : (NSString *) tableName withMaxField : (NSString *) maxField {
    [[DBManager getSharedInstance] openDB];
    
    NSString *sqlSelectMax = [[NSString alloc] initWithFormat:@"SELECT COALESCE(MAX(%@), 0) + 1 as MAX_FIELD FROM %@", tableName, maxField];
    
    NSMutableArray *dados = [[DBManager getSharedInstance] executeQuery:sqlSelectMax];
    
    NSInteger retorno = 1;
    
    if (dados.count > 0) {
        NSDictionary *row = dados[0];
        
        retorno = [[row valueForKey:@"MAX_FIELD"] integerValue];
    }
    
    [[DBManager getSharedInstance] closeDB];
    
    // Se nao achou dados, vai começar do valor 1.
    return retorno;
}

@end
