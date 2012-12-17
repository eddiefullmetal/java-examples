function () { 
	emit(this.product.$id, { 
		rating: this.rating, 
		count: 1 
	});
}