//
//  TokenDao.h
//  Minhas Contas.me
//
//  Created by Orlando Burli on 21/11/14.
//  Copyright (c) 2014 Orlando Burli Projetos de TI Personalizados. All rights reserved.
//

#import "BaseDao.h"
#import "TokenVo.h"

@interface TokenDao : BaseDao

-(void) insert: (TokenVo *) token;

-(void) update: (TokenVo *) token;

-(TokenVo *) get: (NSInteger ) idToken;

-(NSArray *) getList;

@end
