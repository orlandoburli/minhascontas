//
//  CategoriaVo.h
//  Minhas Contas.me
//
//  Created by Orlando Burli on 23/11/14.
//  Copyright (c) 2014 Orlando Burli Projetos de TI Personalizados. All rights reserved.
//


#import <Foundation/Foundation.h>

static NSString *const tipoCategoriaDespesa = @"D";
static NSString *const tipoCategoriaReceita = @"R";

@interface CategoriaVo : NSObject

@property NSInteger idCategoria;
@property NSString *nomeCategoria;
@property NSString *tipoCategoria;
@property NSInteger idCategoriaPai;
@property NSString *idCategoriaReferencia;
@property NSString *sincronizado;

@end
