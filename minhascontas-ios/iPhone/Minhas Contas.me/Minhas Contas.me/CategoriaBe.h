//
//  CategoriaBe.h
//  Minhas Contas.me
//
//  Created by Orlando Burli on 24/11/14.
//  Copyright (c) 2014 Orlando Burli Projetos de TI Personalizados. All rights reserved.
//

#import "BaseBe.h"
#import "CategoriaVo.h"
#import "CategoriaDao.h"

@interface CategoriaBe : BaseBe

-(void) save : (CategoriaVo *) categoria;

-(void) delete : (CategoriaVo *) categoria;

-(NSArray *) getListPai;

@end
