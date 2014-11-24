//
//  HomeViewController.h
//  Minhas Contas.me
//
//  Created by Orlando Burli on 17/11/14.
//  Copyright (c) 2014 Orlando Burli Projetos de TI Personalizados. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "BotaoSistema.h"

@interface HomeViewController : UIViewController

@property (strong, nonatomic) IBOutlet BotaoSistema *btnCategorias;

- (IBAction)cliqueCategorias:(id)sender;

@end
