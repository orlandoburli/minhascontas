//
//  CategoriaDao.h
//  Minhas Contas.me
//
//  Created by Orlando Burli on 24/11/14.
//  Copyright (c) 2014 Orlando Burli Projetos de TI Personalizados. All rights reserved.
//

#import "BaseDao.h"
#import "CategoriaVo.h"

@interface CategoriaDao : BaseDao

-(void) insert: (CategoriaVo *) categoria;

-(void) update: (CategoriaVo *) categoria;

-(void) delete : (CategoriaVo *) categoria;

-(CategoriaVo *) get: (NSInteger ) idCategoria;

-(NSArray *) getListPai;


@end
