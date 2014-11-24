//
//  ViewController.h
//  Minhas Contas.me
//
//  Created by Orlando Burli on 15/10/14.
//  Copyright (c) 2014 Orlando Burli Projetos de TI Personalizados. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "BaseViewController.h"

@interface ViewController : BaseViewController

@property (strong, nonatomic) IBOutlet UITextField *txtEmail;

@property (strong, nonatomic) IBOutlet UITextField *txtSenha;

@property (strong, nonatomic) IBOutlet UIButton *btnEntrar;

@end

