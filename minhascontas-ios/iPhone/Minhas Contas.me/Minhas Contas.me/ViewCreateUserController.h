//
//  ViewCreateUserController.h
//  Minhas Contas.me
//
//  Created by Orlando Burli on 05/11/14.
//  Copyright (c) 2014 Orlando Burli Projetos de TI Personalizados. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "BaseViewController.h"
#import "BotaoSistema.h"

@interface ViewCreateUserController : BaseViewController

@property (strong, nonatomic) IBOutlet UITextField *txtNome;
@property (strong, nonatomic) IBOutlet UITextField *txtSobreNome;
@property (strong, nonatomic) IBOutlet UITextField *txtEmail;
@property (strong, nonatomic) IBOutlet UITextField *txtSenha;

@property (strong, nonatomic) IBOutlet BotaoSistema *btnCriarUsuario;
@property (strong, nonatomic) IBOutlet BotaoSistema *btnVoltar;

@end
