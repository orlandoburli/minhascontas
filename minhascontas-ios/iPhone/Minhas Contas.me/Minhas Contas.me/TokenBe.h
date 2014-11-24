//
//  TokenBe.h
//  Minhas Contas.me
//
//  Created by Orlando Burli on 23/11/14.
//  Copyright (c) 2014 Orlando Burli Projetos de TI Personalizados. All rights reserved.
//

#import "BaseBe.h"
#import "TokenVo.h"
#import "TokenDao.h"

@interface TokenBe : BaseBe

-(void) save: (TokenVo *) token;

-(TokenVo *) getLastToken;

@end
