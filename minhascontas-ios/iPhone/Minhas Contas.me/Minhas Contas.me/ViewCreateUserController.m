//
//  ViewCreateUserController.m
//  Minhas Contas.me
//
//  Created by Orlando Burli on 05/11/14.
//  Copyright (c) 2014 Orlando Burli Projetos de TI Personalizados. All rights reserved.
//

#import "ViewCreateUserController.h"
#import "ViewController.h"
#import "AcessoServices.h"

@interface ViewCreateUserController ()

@end

@implementation ViewCreateUserController

- (void)viewDidLoad {
    [super viewDidLoad];
    
    UITapGestureRecognizer *tap = [[UITapGestureRecognizer alloc]
                                   initWithTarget:self
                                   action:@selector(dismissKeyboard)];
    
    [self.view addGestureRecognizer:tap];
    
    self.txtNome.delegate = self;
    self.txtSobreNome.delegate = self;
    self.txtEmail.delegate = self;
    self.txtSenha.delegate = self;

    // Foco no primeiro input
//    [self.txtNome becomeFirstResponder];
}

- (BOOL)acceptsFirstResponder {
    return YES;
}

- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
}

- (IBAction)criarUsuario:(id)sender {

    // Esconde o teclado
    [self dismissKeyboard];
    
    AcessoServices *services = [[AcessoServices alloc] init];
    
    RetornoVo *retorno = [services criarUsuario:self.txtNome.text withSobrenome:self.txtSobreNome.text withEmail:self.txtEmail.text withSenha:self.txtSenha.text];
    
    if (retorno.sucesso) {
        [self showTimedAlert:retorno.mensagem withTitulo:@"Informação"];

        [self voltar: self.btnVoltar];
    } else {
        // Ocorreu algo errado!!!
        [self showTimedAlert:retorno.mensagem withTitulo:@"Aviso"];
        
        if ([retorno.fieldFocus isEqualToString:@"nomeUsuario"]) {
            [self.txtNome becomeFirstResponder];
        } else if ([retorno.fieldFocus isEqualToString:@"sobreNomeUsuario"]) {
            [self.txtSobreNome becomeFirstResponder];
        } else if ([retorno.fieldFocus isEqualToString:@"email"]) {
            [self.txtEmail becomeFirstResponder];
        } else if ([retorno.fieldFocus isEqualToString:@"senha"]) {
            [self.txtSenha becomeFirstResponder];
        }
    }
    
    NSLog(@"Processo finalizado!");
}

- (IBAction)voltar:(id)sender {
    
    ViewController *homeView = [self.storyboard instantiateViewControllerWithIdentifier:@"ViewLogin"];
    
    [self.navigationController pushViewController:homeView animated:YES];
    
    [self presentViewController:homeView animated:YES completion:nil];
}

-(void)dismissKeyboard {
    [self.txtNome resignFirstResponder];
    [self.txtSobreNome resignFirstResponder];
    [self.txtEmail resignFirstResponder];
    [self.txtSenha resignFirstResponder];
}

- (BOOL)textFieldShouldReturn:(UITextField *)textField {

    [textField resignFirstResponder];
    
    if (textField == self.txtNome) {
        [self.txtSobreNome becomeFirstResponder];
    } else if (textField == self.txtSobreNome) {
        [self.txtEmail becomeFirstResponder];
    } else if (textField == self.txtEmail) {
        [self.txtSenha becomeFirstResponder];
    } else if (textField == self.txtSenha) {
        // Clique do botao Criar usuário
        [self criarUsuario:self.btnCriarUsuario];
    }
    return NO;
}



@end
