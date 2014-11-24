//
//  HomeViewController.m
//  Minhas Contas.me
//
//  Created by Orlando Burli on 17/11/14.
//  Copyright (c) 2014 Orlando Burli Projetos de TI Personalizados. All rights reserved.
//

#import "HomeViewController.h"
#import "AppGlobal.h"

@interface HomeViewController ()

@property (strong, nonatomic) IBOutlet UILabel *lblWelcome;

@end

@implementation HomeViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    
    AppGlobal *app = [AppGlobal instance];

    self.lblWelcome.text = [NSString stringWithFormat:@"Seja bem vindo, %@", app.token.usuario.nomeUsuario];
}

- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

/*
#pragma mark - Navigation

// In a storyboard-based application, you will often want to do a little preparation before navigation
- (void)prepareForSegue:(UIStoryboardSegue *)segue sender:(id)sender {
    // Get the new view controller using [segue destinationViewController].
    // Pass the selected object to the new view controller.
}
*/

- (IBAction)cliqueCategorias:(id)sender {
    
}
@end
