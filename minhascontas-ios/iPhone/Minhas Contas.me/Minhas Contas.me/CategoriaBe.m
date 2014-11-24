//
//  CategoriaBe.m
//  Minhas Contas.me
//
//  Created by Orlando Burli on 24/11/14.
//  Copyright (c) 2014 Orlando Burli Projetos de TI Personalizados. All rights reserved.
//

#import "CategoriaBe.h"

@implementation CategoriaBe

-(void) save : (CategoriaVo *) categoria {
    CategoriaDao *dao = [[CategoriaDao alloc]init];
    
    if (categoria.idCategoria > 0) {
        [dao update:categoria];
    } else {
        [dao insert:categoria];
    }
}

-(void) delete : (CategoriaVo *) categoria {
    CategoriaDao *dao = [[CategoriaDao alloc]init];

    [dao delete:categoria];
}

-(NSArray *) getListPai {
    CategoriaDao *dao = [[CategoriaDao alloc]init];

    return [dao getListPai];
}

@end
